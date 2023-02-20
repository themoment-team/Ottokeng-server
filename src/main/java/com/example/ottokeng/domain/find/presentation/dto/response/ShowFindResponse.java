package com.example.ottokeng.domain.find.presentation.dto.response;

import com.example.ottokeng.domain.find.entity.Acquisition;
import com.example.ottokeng.domain.find.entity.FindWriting;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@RequiredArgsConstructor
public class ShowFindResponse {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String detail;

    @Enumerated(EnumType.STRING)
    private Acquisition acquisition;

    private String image;

    private String address;

    private String communication;

    public ShowFindResponse(FindWriting findWriting){
        this.id = findWriting.getId();
        this.title = findWriting.getTitle();
        this.acquisition = findWriting.getAcquisition();
        this.image = findWriting.getImage();
        this.address = findWriting.getAddress();
        this.communication = findWriting.getCommunication();
    }
}
