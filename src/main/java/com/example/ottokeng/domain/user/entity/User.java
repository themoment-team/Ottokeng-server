package com.example.ottokeng.domain.user.entity;

import com.example.ottokeng.domain.find.entity.Find;
import com.example.ottokeng.domain.lost.entity.Post;
import com.example.ottokeng.global.enumType.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Post> lostWriting;

    public User update(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        return this;
    }
}

