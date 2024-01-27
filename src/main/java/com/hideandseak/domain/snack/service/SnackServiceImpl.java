package com.hideandseak.domain.snack.service;

import com.hideandseak.domain.drink.domain.DrinkEntity;
import com.hideandseak.domain.drink.domain.repository.DrinkRepository;
import com.hideandseak.domain.drink.dto.req.AddDrinkRequest;
import com.hideandseak.domain.drink.exception.DrinkErrorException;
import com.hideandseak.domain.snack.domain.repository.SnackEntity;
import com.hideandseak.domain.snack.domain.repository.SnackRepository;
import com.hideandseak.domain.snack.dto.req.AddSnackRequest;
import com.hideandseak.domain.snack.exception.SnackErrorException;
import com.hideandseak.domain.user.domain.repository.UserRepository;
import com.hideandseak.global.common.BaseResponse;
import com.hideandseak.global.infra.S3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SnackServiceImpl implements SnackService{

    private final SnackRepository snackRepository;
    private final S3Uploader s3Uploader;
    @Override
    public BaseResponse addSnack(Authentication authentication, AddSnackRequest addSnackRequest, MultipartFile multipartFile) throws IOException {
        //if (userRepository.findByUserAccount(authentication.getName()).get().getRole().equals(Role.ADMIN)) {
        String image = s3Uploader.upload(multipartFile, "snack");
        SnackEntity snack = dtoToEntity(addSnackRequest, image);
        snackRepository.save(snack);
        //}

        return new BaseResponse(HttpStatus.OK, "저장성공");
    }

    @Override
    public BaseResponse getSnacks() {
        return new BaseResponse(
                HttpStatus.OK,
                "안주 불러오기 성공",
                snackRepository.findAll().stream().map(this::entityToDto)
        );
    }

    @Override
    public BaseResponse getSnack(Long id) {
        return new BaseResponse(HttpStatus.OK,
                "안주 단건 불러오기 성공",
                entityToDto(snackRepository.findById(id).orElseThrow(SnackErrorException::NOT_FOUND))
        );
    }
}