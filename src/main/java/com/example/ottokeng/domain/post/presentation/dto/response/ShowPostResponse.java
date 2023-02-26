package com.example.ottokeng.domain.post.presentation.dto.response;

import com.example.ottokeng.domain.post.entity.Post;
import com.example.ottokeng.domain.post.entity.Get;
import com.example.ottokeng.domain.post.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ShowPostResponse {
    private Long id;
    private String title;
    private String contents;
    private String name;
    private Get acquire;
    private String image;
    private String address;
    private String communication;
    private Type type;
    private LocalDateTime createdAt;

    public ShowPostResponse(Post post, String name){
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.name = name;
        this.acquire = post.getAcquire();
        this.image = post.getImage();
        this.address = post.getAddress();
        this.communication = post.getCommunication();
        this.type = post.getType();
        this.createdAt = post.getCreatedAt();
    }
}
