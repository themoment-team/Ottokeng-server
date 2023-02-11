package com.example.ottokeng.domain.lost.entity;

import com.example.ottokeng.domain.find.entity.Find;
import com.example.ottokeng.domain.find.entity.Get;
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

    @ManyToOne
    @JoinColumn(name = "lost")
    private Lost lost;
}
