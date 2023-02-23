package com.example.ottokeng.domain.lost.entity;

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
public class LostWriting {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String detail;

    @Enumerated(EnumType.STRING)
    private Relay relay;

    private String image;

    private String address;

    private String communication;

    public void update(String title, String detail, Relay relay, String image, String address, String communication){
        this.title = title;
        this.detail = detail;
        this.relay = relay;
        this.image = image;
        this.address = address;
        this.communication = communication;
    }

    @ManyToOne
    @JoinColumn(name = "lost")
    private Lost lost;
}
