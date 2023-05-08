package com.example.miniproject.controller;

import com.example.miniproject.dto.BasicResponseDto;
import com.example.miniproject.dto.CommentRequestDto;
import com.example.miniproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.example.miniproject.security.UserDetailsImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{quiz-id}")
    public BasicResponseDto<?> createComment (@PathVariable(name = "quiz-id")Long id, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.createComment(id, commentRequestDto, userDetails.getUser());
    }


    @DeleteMapping("/{comment-id}")
    public BasicResponseDto<?> deleteComment(@PathVariable(name = "comment-id") Long id) {
        return commentService.deleteComment(id);
    }

}
