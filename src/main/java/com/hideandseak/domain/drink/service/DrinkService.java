package com.hideandseak.domain.drink.service;

import com.hideandseak.domain.drink.domain.DrinkEntity;
import com.hideandseak.domain.drink.dto.req.AddDrinkRequest;
import com.hideandseak.domain.drink.dto.res.DrinkResponse;
import com.hideandseak.global.common.BaseResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DrinkService {
    BaseResponse addDrink(Authentication authentication, AddDrinkRequest addDrinkRequest, MultipartFile multipartFile) throws IOException;

    BaseResponse getDrinks();

    BaseResponse clickDrink(Long id);

    BaseResponse getDrinkToCategory(String category);

    default DrinkResponse entityToDto(DrinkEntity drink){
        return DrinkResponse.builder()
                .id(drink.getId())
                .image(drink.getImage())
                .name(drink.getName())
                .price(drink.getPrice())
                .category(drink.getCategory())
                .data(drink.getData())
                .description(drink.getDescription())
                .link(drink.getLink())
                .build();
    }

    default DrinkEntity dtoToEntity(AddDrinkRequest addDrinkRequest, String image){
        return DrinkEntity.builder()
                .image(image)
                .name(addDrinkRequest.name())
                .price(addDrinkRequest.price())
                .category(addDrinkRequest.category())
                .data(addDrinkRequest.data())
                .description(addDrinkRequest.description())
                .link(addDrinkRequest.link())
                .build();
    }

}

