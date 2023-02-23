package com.example.ottokeng.domain.find.presentation.dto.response;

import com.example.ottokeng.domain.find.entity.Acquisition;
import com.example.ottokeng.domain.find.entity.FindWriting;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ShowFindResponse {
    private Long id;
    private String title;
    private String detail;
    private Acquisition acquisition;
    private String image;
    private String address;
    private String communication;

    public ShowFindResponse(FindWriting findWriting){
        this.id = findWriting.getId();
        this.title = findWriting.getTitle();
        this.detail = findWriting.getDetail();
        this.acquisition = findWriting.getAcquisition();
        this.image = findWriting.getImage();
        this.address = findWriting.getAddress();
        this.communication = findWriting.getCommunication();
    }
}
