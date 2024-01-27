package com.hideandseak.domain.user.controller;

import com.hideandseak.domain.user.dto.req.UserJoinRequest;
import com.hideandseak.domain.user.dto.req.UserLoginRequest;
import com.hideandseak.domain.user.dto.req.UserRefreshRequest;
import com.hideandseak.domain.user.service.UserService;
import com.hideandseak.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public BaseResponse join(@RequestBody UserJoinRequest userJoinRequest){
        System.out.println(userJoinRequest.userAccount() + " " + userJoinRequest.password());
        return userService.join(userJoinRequest);
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody UserLoginRequest userLoginRequest){
        return userService.login(userLoginRequest);
    }

    @PostMapping("/refresh")
    public BaseResponse refreshToAccessToken(@RequestBody UserRefreshRequest userRefreshRequest){
        return userService.refreshToAccessToken(userRefreshRequest);
    }

    @GetMapping("/subscription")
    public BaseResponse myProfile(Authentication authentication){
        return userService.myProfile(authentication);
    }

    @PostMapping("/subscription")
    public BaseResponse subscription(Authentication authentication){
        return userService.subscription(authentication);
    }

    @PostMapping("/subscription/cancel")
    public BaseResponse cancelSubscription(Authentication authentication){
        return userService.cancelSubscription(authentication);
    }

}
