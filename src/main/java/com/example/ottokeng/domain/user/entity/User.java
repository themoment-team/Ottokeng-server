package com.example.ottokeng.domain.user.entity;

import com.example.ottokeng.domain.find.entity.Find;
import com.example.ottokeng.domain.lost.entity.Lost;
import com.example.ottokeng.global.enumType.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String imageUrl;

    private String oauthId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @JoinColumn(name = "find_id")
    private Find find;

    @OneToOne
    @JoinColumn(name = "lost_id")
    private Lost lost;

    public User update(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        return this;
    }
}

