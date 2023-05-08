package com.example.miniproject.dto;

import com.example.miniproject.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {

    private Long id;
    private String userId;
    private String comment;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getUser().getUserId();
        this.comment = comment.getComment();
    }

}
