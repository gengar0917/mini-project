package com.example.miniproject.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Setter
@NoArgsConstructor
public class SolvedQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private boolean solved;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)// CASCADE 설정 고민
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public SolvedQuiz(User user) {
        this.user = user;
    }

    public boolean getSolved() {
        return this.solved;
    }


}
