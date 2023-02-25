package com.example.ottokeng.domain.post.presentation.dto.response;

import com.example.ottokeng.domain.post.entity.Post;
import com.example.ottokeng.domain.post.entity.Get;
import com.example.ottokeng.domain.post.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

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

    public ShowPostResponse(Post lostWriting){
        this.id = lostWriting.getId();
        this.title = lostWriting.getTitle();
        this.contents = lostWriting.getContents();
        this.name = lostWriting.getUser().getName();
        this.acquire = lostWriting.getAcquire();
        this.image = lostWriting.getImage();
        this.address = lostWriting.getAddress();
        this.communication = lostWriting.getCommunication();
        this.type = lostWriting.getType();
    }
}
