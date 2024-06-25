package com.bookhive.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "review_opinion")
@AllArgsConstructor@NoArgsConstructor
@Getter
@Setter
public class ReviewOpinionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String opinion;

    @Column(nullable = false)
    private LocalDateTime created_by;

    @ManyToOne
    private ExpertReviewsEntity expertReviewsEntity;


}
