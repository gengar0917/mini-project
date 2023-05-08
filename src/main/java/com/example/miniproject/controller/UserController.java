package com.example.miniproject.controller;

import com.example.miniproject.dto.LoginRequestDto;
import com.example.miniproject.dto.SignupRequestDto;
import com.example.miniproject.dto.BasicResponseDto;
import com.example.miniproject.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("/signup")
    public BasicResponseDto<?> signup(@RequestBody SignupRequestDto signupRequestDto){
        return userService.signup(signupRequestDto);
    }

    //아이디 중복 확인
    @PostMapping("/signup/valid")
    public BasicResponseDto<String> checkId(@RequestBody Map<String, String> userId){
        return userService.checkId(userId);
    }

    //로그인하기
    @PostMapping("/login")
    public BasicResponseDto<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response ) {
        return userService.login(loginRequestDto, response);
    }
}
