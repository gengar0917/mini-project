package com.example.miniproject.service;

import com.example.miniproject.dto.BasicResponseDto;
import com.example.miniproject.dto.CommentRequestDto;
import com.example.miniproject.entity.Comment;
import com.example.miniproject.entity.Quiz;
import com.example.miniproject.entity.User;
import com.example.miniproject.repository.CommentRepository;
import com.example.miniproject.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final QuizRepository quizRepository;

    //댓글 생성
    public BasicResponseDto<?> createComment(Long id, CommentRequestDto commentRequestDto, User user){
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 퀴즈가 존재하지 않습니다."));

        Comment comment = new Comment(commentRequestDto);

        quiz.addComment(comment);
        comment.setUser(user);

        commentRepository.save(comment);

        return BasicResponseDto.setSuccess("해당 댓글에 댓글을 등록했습니다!", null);
    }

    //댓글 삭제
    public BasicResponseDto<?> deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        commentRepository.delete(comment);

        return BasicResponseDto.setSuccess("해당 댓글을 삭제하였습니다!", null);
    }
}
