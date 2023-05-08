package com.example.miniproject.entity;

import com.example.miniproject.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long solvedQuizCnt = 0L;

//    public User(SignupRequestDto signupRequestDto) {
//        this.userId = signupRequestDto.getUserId();
//        this.password = signupRequestDto.getPassword();
//    }
    public User(String userId, String password){
        this.userId = userId;
        this.password = password;
    }
}
