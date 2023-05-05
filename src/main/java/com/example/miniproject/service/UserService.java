package com.example.miniproject.service;

import com.example.miniproject.dto.LoginRequestDto;
import com.example.miniproject.dto.SignupRequestDto;
import com.example.miniproject.dto.BasicResponseDto;
import com.example.miniproject.entity.User;
import com.example.miniproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    
    public BasicResponseDto<?> signup(SignupRequestDto signupRequestDto) {
        User user = new User(signupRequestDto);

        userRepository.save(user);
        return BasicResponseDto.setSuccess("회원 가입 완료!", null);
    }

    public BasicResponseDto<?> login(LoginRequestDto loginRequestDto) {
        String userId = loginRequestDto.getUserId();
        String password = loginRequestDto.getPassword();

        // 사용자가 존재 여부
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        //비밀번호 일치여부
        if (!password.equals(user.getPassword())) {
            return BasicResponseDto.setFailed("로그인 실패!");
        }

        return BasicResponseDto.setSuccess("로그인 성공!", null);
    }

}
