package com.hideandseak.domain.drink.service;

import com.hideandseak.domain.drink.domain.DrinkEntity;
import com.hideandseak.domain.drink.domain.repository.DrinkRepository;
import com.hideandseak.domain.drink.dto.req.AddDrinkRequest;
import com.hideandseak.domain.drink.exception.DrinkErrorException;
import com.hideandseak.global.common.BaseResponse;
//import com.hideandseak.global.infra.S3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DrinkServiceImpl implements DrinkService{

    private final DrinkRepository drinkRepository;
//    private final S3Uploader s3Uploader;
    @Override
    public BaseResponse addDrink(Authentication authentication,
                                 AddDrinkRequest addDrinkRequest,
                                 MultipartFile multipartFile) throws IOException {

        //if (userRepository.findByUserAccount(authentication.getName()).get().getRole().equals(Role.ADMIN)) {
//            String image = s3Uploader.upload(multipartFile, "drink");
//            DrinkEntity drink = dtoToEntity(addDrinkRequest, image);
//            drinkRepository.save(drink);
        //}

        return new BaseResponse(HttpStatus.OK, "저장성공");
    }

    @Override
    public BaseResponse getDrinks() {
        List<DrinkEntity> drinkEntities = drinkRepository.findAll();
        return new BaseResponse(HttpStatus.OK,
                "술 불러오기 성공",
                drinkEntities.stream().map(this::entityToDto));
    }

    @Override
    public BaseResponse clickDrink(Long id) {
        return new BaseResponse(HttpStatus.OK,
                "술 불러오기 성공",
                entityToDto(drinkRepository.findById(id).orElseThrow(DrinkErrorException::NOT_FOUND)));
    }

    @Override
    public BaseResponse getDrinkToCategory(String category) {
        List<DrinkEntity> drinkEntities = drinkRepository.findByCategoryContaining(category);
        return new BaseResponse(HttpStatus.OK,
                "술 불러오기 성공",
                drinkEntities.stream().map(this::entityToDto));
    }


}
