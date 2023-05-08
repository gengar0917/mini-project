package com.example.miniproject.service;

import com.example.miniproject.dto.LoginRequestDto;
import com.example.miniproject.dto.SignupRequestDto;
import com.example.miniproject.dto.BasicResponseDto;
import com.example.miniproject.entity.User;
import com.example.miniproject.jwt.JwtUtil;
import com.example.miniproject.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    
    public BasicResponseDto<?> signup(SignupRequestDto signupRequestDto) {
        String userId = signupRequestDto.getUserId();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        User user = new User(userId, password);
        userRepository.save(user);
        return BasicResponseDto.setSuccess("회원 가입 완료!", null);
    }

    public BasicResponseDto<String> checkId(Map<String, String> userId){
        String id = String.valueOf(userId.get("userId"));
        Optional<User> found = userRepository.findByUserId(id);

        if (found.isPresent()) {
            return BasicResponseDto.setFailed("중복된 아이디입니다.");
        }
        return BasicResponseDto.setSuccess("사용 가능한 아이디입니다.", null);
    }

    public BasicResponseDto<?> login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String userId = loginRequestDto.getUserId();
        String password = loginRequestDto.getPassword();

        // 사용자가 존재 여부
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        //비밀번호 일치여부
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return BasicResponseDto.setFailed("비밀번호가 일치하지 않습니다.");
        }
        
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUserId()));

        return BasicResponseDto.setSuccess("로그인 성공!", null);
    }
}