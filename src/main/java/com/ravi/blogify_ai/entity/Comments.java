package com.ravi.blogify_ai.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Blogs blogId;

    @ManyToOne
    private User userId;

    private String content;

    private Date createdAt;
}
