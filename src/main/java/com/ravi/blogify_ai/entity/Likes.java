package com.ravi.blogify_ai.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    private Blogs blogId;

    @OneToOne
    private User userId;

    private Date createdAt;
}
