package com.bookhive.model.entity;

import com.bookhive.model.entity.AuthorEntity;
import com.bookhive.model.enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String cover;

    @ManyToOne
    private AuthorEntity author;

    @Column(nullable = false)
    private String form;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    private Double rating;


}
