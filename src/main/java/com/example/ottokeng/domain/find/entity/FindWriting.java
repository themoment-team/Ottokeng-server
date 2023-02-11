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
    @GeneratedValue
    private Long id;

    private String title;

    private String detail;

    @Enumerated(EnumType.STRING)
    private Get get;

    private String image;

    private String address;

    private String communication;

    @ManyToOne
    @JoinColumn(name = "find")
    private Find find;
}
