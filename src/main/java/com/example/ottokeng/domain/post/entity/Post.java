package com.example.ottokeng.domain.post.entity;

import com.example.ottokeng.domain.comment.entity.Comment;
import com.example.ottokeng.domain.user.entity.User;
import com.example.ottokeng.global.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String contents;

    @Enumerated(EnumType.STRING)
    private Get acquire;

    private String image;

    private String address;

    private String communication;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();


    public void update(String title, String contents, Get acquire, String image, String address, String communication, Type type){
        this.title = title;
        this.contents = contents;
        this.acquire = acquire;
        this.image = image;
        this.address = address;
        this.communication = communication;
        this.type = type;
    }

}
