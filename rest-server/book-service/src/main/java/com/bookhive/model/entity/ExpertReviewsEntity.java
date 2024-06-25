package com.bookhive.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "expert_reviews")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExpertReviewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String review;

    @Column(nullable = false)
    private LocalDateTime created_by;

    @ManyToOne
    private AuthorEntity author;

    @ManyToOne
    private BookEntity reviewBook;
}
