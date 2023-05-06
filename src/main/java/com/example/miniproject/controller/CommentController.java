package com.example.miniproject.controller;

import com.example.miniproject.dto.BasicResponseDto;
import com.example.miniproject.dto.CommentRequestDto;
import com.example.miniproject.dto.CommentResponseDto;
import com.example.miniproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
public class CommentController {

//    private final CommentService commentService;

//    @PostMapping("/{id}")
//    public BasicResponseDto createComment (@RequestBody CommentRequestDto commentRequestDto, UserDetails userDetails){
//        commentService.createComment(commentRequestDto, userDetails.g);
//    }
//
//
//    @DeleteMapping("/{id}")
//    public BasicResponseDto deleteComment(@PathVariable Long id) {
//        return commentService.deleteComment(id);
//    }

}
