package com.example.ottokeng.domain.lost.entity;

import com.example.ottokeng.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String contents;

    private String date;

    @Enumerated(EnumType.STRING)
    private Get acquire;

    private String image;

    private String address;

    private String communication;

    @Enumerated(EnumType.STRING)
    private Type type;

    public void update(String title, String contents, String date, Get acquire, String image, String address, String communication, Type type){
        this.title = title;
        this.contents = contents;
        this.date = date;
        this.acquire = acquire;
        this.image = image;
        this.address = address;
        this.communication = communication;
        this.type = type;
    }

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
}
