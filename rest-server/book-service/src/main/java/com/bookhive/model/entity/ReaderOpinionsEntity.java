package com.bookhive.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "readers_opinions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReaderOpinionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String opinion;

    @Column(nullable = false)
    private LocalDateTime created_on;

    @ManyToOne
    private BookEntity book;
}
