package com.hideandseak.domain.user.service;

import com.hideandseak.domain.user.domain.UserEntity;
import com.hideandseak.domain.user.dto.req.UserJoinRequest;
import com.hideandseak.domain.user.dto.req.UserLoginRequest;
import com.hideandseak.domain.user.dto.req.UserRefreshRequest;
import com.hideandseak.global.common.BaseResponse;
import org.springframework.security.core.Authentication;

public interface UserService {
    BaseResponse join(UserJoinRequest userJoinRequest);

    BaseResponse login(UserLoginRequest userLoginRequest);

    BaseResponse refreshToAccessToken(UserRefreshRequest userRefreshRequest);

    BaseResponse myProfile(Authentication authentication);

    UserEntity getUserByUserAccount(String userAccount);

    BaseResponse subscription(Authentication authentication);

    BaseResponse cancelSubscription(Authentication authentication);

    default UserEntity joinToEntity(UserJoinRequest userJoinRequest){
        return UserEntity.builder()
                .userAccount(userJoinRequest.userAccount())
                .password(userJoinRequest.password())
                .anything(userJoinRequest.anything())
                .build();
    }

}
