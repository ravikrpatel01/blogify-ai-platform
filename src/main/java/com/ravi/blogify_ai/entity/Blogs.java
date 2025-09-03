package com.ravi.blogify_ai.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Blogs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String content;

    @ManyToOne
    private User authorId;

    private String tags;

    private Date createdAt;

    private Date updatedAt;
}
