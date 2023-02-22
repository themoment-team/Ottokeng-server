package com.example.ottokeng.domain.find.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindWriting {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String detail;

    private String date;

    private String name;

    @Enumerated(EnumType.STRING)
    private Acquisition acquisition;

    private String image;

    private String address;

    private String communication;

    public void update(String title, String detail, String name, Acquisition acquisition, String image, String address, String communication){
        this.title = title;
        this.detail = detail;
        this.name = name;
        this.acquisition = acquisition;
        this.image = image;
        this.address = address;
        this.communication = communication;
    }

    @ManyToOne
    @JoinColumn(name = "find")
    private Find find;
}
