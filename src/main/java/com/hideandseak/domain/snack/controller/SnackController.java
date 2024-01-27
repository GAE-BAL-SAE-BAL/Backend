package com.hideandseak.domain.snack.controller;

import com.hideandseak.domain.snack.dto.req.AddSnackRequest;
import com.hideandseak.domain.snack.service.SnackService;
import com.hideandseak.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/snack")
public class SnackController {

    private final SnackService snackService;

    @PostMapping("/add")
    public BaseResponse addSnack(Authentication authentication,
                                 @RequestPart("data") AddSnackRequest AddSnackRequest,
                                 @RequestPart("image") MultipartFile multipartFile) throws IOException {

        return snackService.addSnack(authentication,AddSnackRequest,multipartFile);
    }

    @GetMapping("/all")
    public BaseResponse getSnacks(){
        return snackService.getSnacks();
    }

    @GetMapping("/{id}")
    public BaseResponse getSnack(@PathVariable Long id){
        return snackService.getSnack(id);
    }
}
