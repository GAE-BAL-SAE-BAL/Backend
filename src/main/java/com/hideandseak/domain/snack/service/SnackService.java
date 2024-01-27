package com.hideandseak.domain.snack.service;

import com.hideandseak.domain.snack.domain.repository.SnackEntity;
import com.hideandseak.domain.snack.dto.req.AddSnackRequest;
import com.hideandseak.domain.snack.dto.res.SnackResponse;
import com.hideandseak.global.common.BaseResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SnackService {

    BaseResponse addSnack(Authentication authentication, AddSnackRequest addSnackRequest, MultipartFile multipartFile) throws IOException;

    BaseResponse getSnacks();

    BaseResponse getSnack(Long id);

    default SnackEntity dtoToEntity(AddSnackRequest addSnackRequest, String image){
        return SnackEntity.builder()
                .image(image)
                .name(addSnackRequest.name())
                .price(addSnackRequest.price())
                .data(addSnackRequest.data())
                .description(addSnackRequest.description())
                .link(addSnackRequest.link())
                .build();
    }
    default SnackResponse entityToDto(SnackEntity snackEntity){
        return SnackResponse.builder()
                .id(snackEntity.getId())
                .image(snackEntity.getImage())
                .name(snackEntity.getName())
                .price(snackEntity.getPrice())
                .data(snackEntity.getData())
                .description(snackEntity.getDescription())
                .link(snackEntity.getLink())
                .build();
    }
}
