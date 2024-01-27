package com.hideandseak.domain.user.service;

import com.hideandseak.domain.user.domain.UserEntity;
import com.hideandseak.domain.user.domain.repository.UserRepository;
import com.hideandseak.domain.user.dto.req.UserJoinRequest;
import com.hideandseak.domain.user.dto.req.UserLoginRequest;
import com.hideandseak.domain.user.dto.req.UserRefreshRequest;
import com.hideandseak.domain.user.dto.res.UserRefreshResponse;
import com.hideandseak.domain.user.exception.AuthErrorException;
import com.hideandseak.global.common.BaseResponse;
import com.hideandseak.global.filter.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encode;

    @Value("${jwt.token.secret}")
    private String secretKey;

    @Override
    public BaseResponse join(UserJoinRequest userJoinRequest){

        if (userJoinRequest.userAccount().isEmpty() || userJoinRequest.password().isEmpty()) {
            throw AuthErrorException.ID_PW_NULL();
        }

        userRepository.findByUserAccount(userJoinRequest.userAccount()).ifPresent(userEntity -> {
            throw AuthErrorException.DUPLICATED();
        });

        UserEntity user = joinToEntity(userJoinRequest);
        String passwordEncoder = encode.encode(user.getPassword());
        user.fixPassword(passwordEncoder);
        //System.out.printf(user.getUserAccount() + "11111" + user.getPassword() + " " + user.getRole());
        userRepository.save(user);

        return new BaseResponse(HttpStatus.OK, "회원가입 성공");
    }

    @Override
    public BaseResponse login(UserLoginRequest userLoginRequest) {

        UserEntity user = userRepository.findByUserAccount(userLoginRequest.userAccount()).orElseThrow(AuthErrorException::NOT_FOUND);
        if (!encode.matches(userLoginRequest.password(), user.getPassword())){
            throw AuthErrorException.NOT_FOUND();
        }

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", JwtTokenUtil.createAccessToken(user.getUserAccount(), secretKey));
        tokens.put("refreshToken", JwtTokenUtil.createRefreshToken(user.getUserAccount(), secretKey));

        return new BaseResponse(HttpStatus.OK, "로그인 성공", tokens);
    }

    @Override
    public BaseResponse refreshToAccessToken(UserRefreshRequest userRefreshRequest) {
        String refreshToken = userRefreshRequest.refreshToken();
        if(refreshToken == null || refreshToken.isEmpty()) throw AuthErrorException.FAIL_TOKEN();

        String newAccessToken = JwtTokenUtil.generateAccessTokenFromRefreshToken(refreshToken, secretKey);

        if (newAccessToken == null || newAccessToken.isEmpty()) throw AuthErrorException.FAIL_TOKEN();

        UserRefreshResponse userRefreshResponse = new UserRefreshResponse(newAccessToken);

        return new BaseResponse(HttpStatus.OK, "토큰 생성 성공", userRefreshResponse);
    }

    @Override
    public BaseResponse myProfile(Authentication authentication) {;
        return new BaseResponse(
                HttpStatus.OK,
                "구독정보",
                userRepository.findByUserAccount(authentication.getName()).get().getSubscribe()
        );
    }

    @Override
    public UserEntity getUserByUserAccount(String userAccount) {
        return userRepository.findByUserAccount(userAccount)
                .orElseThrow(AuthErrorException::NOT_FOUND);
    }

    @Override
    public BaseResponse subscription(Authentication authentication) {
        UserEntity user = userRepository.findByUserAccount(authentication.getName()).get();
        user.subscribe();
        userRepository.save(user);
        return new BaseResponse(HttpStatus.OK, "구독성공");
    }

    @Override
    public BaseResponse cancelSubscription(Authentication authentication) {
        UserEntity user = userRepository.findByUserAccount(authentication.getName()).get();
        user.cancelSubscribe();
        userRepository.save(user);
        return new BaseResponse(HttpStatus.OK, "구독 취소 성공");
    }
}
