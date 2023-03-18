package com.example.ottokeng.domain.post.presentation.dto.response;

import com.example.ottokeng.domain.post.entity.Post;
import com.example.ottokeng.domain.post.entity.Get;
import com.example.ottokeng.domain.post.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class ShowPostResponse {
    private Long id;
    private String title;
    private String contents;
    private String writer;
    private Get acquire;
    private String lat;
    private String lng;
    private Type type;
    private LocalDateTime createdAt;
    private List<String> imageUrls = new ArrayList<>();

    public ShowPostResponse(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.writer = post.getUser().getName();
        this.acquire = post.getAcquire();
        this.lat = post.getLat();
        this.lng = post.getLng();
        this.type = post.getType();
        this.createdAt = post.getCreatedAt();

        post.getImages().stream()
                .map((image) -> this.imageUrls.add(image.getImageUrl()))
                .collect(Collectors.toList());
    }
}
