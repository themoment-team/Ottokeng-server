package com.example.ottokeng.domain.lost.presentation.dto.response;

import com.example.ottokeng.domain.find.entity.Acquisition;
import com.example.ottokeng.domain.find.entity.FindWriting;
import com.example.ottokeng.domain.lost.entity.LostWriting;
import com.example.ottokeng.domain.lost.entity.Relay;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowLostResponse {
    private Long id;
    private String title;
    private String detail;
    private Relay relay;
    private String image;
    private String address;
    private String communication;

    public ShowLostResponse(LostWriting lostWriting){
        this.id = lostWriting.getId();
        this.title = lostWriting.getTitle();
        this.detail = lostWriting.getDetail();
        this.relay = lostWriting.getRelay();
        this.image = lostWriting.getImage();
        this.address = lostWriting.getAddress();
        this.communication = lostWriting.getCommunication();
    }
}
