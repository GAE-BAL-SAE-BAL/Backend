package com.hideandseak.domain.drink.controller;


import com.hideandseak.domain.drink.dto.req.AddDrinkRequest;
import com.hideandseak.domain.drink.service.DrinkService;
import com.hideandseak.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/drink")
public class DrinkController {

    private final DrinkService drinkService;

    @PostMapping("/add")
    public BaseResponse addDrink(Authentication authentication,
                                 @RequestPart("data") AddDrinkRequest addDrinkRequest,
                                 @RequestPart("image") MultipartFile multipartFile) throws IOException {

        return drinkService.addDrink(authentication,addDrinkRequest,multipartFile);
    }

    @GetMapping("/all")
    public BaseResponse getDrinks(){
        return drinkService.getDrinks();
    }

    @GetMapping("/{id}")
    public BaseResponse clickDrink(@PathVariable Long id){
        return drinkService.clickDrink(id);
    }

    @GetMapping("")
    public BaseResponse getDrinkToCategory(@RequestParam("category") String category){
        return drinkService.getDrinkToCategory(category);
    }

}
