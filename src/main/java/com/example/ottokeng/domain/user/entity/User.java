package com.example.ottokeng.domain.user.entity;

<<<<<<< HEAD
import com.example.ottokeng.domain.post.entity.Post;
=======
import com.example.ottokeng.domain.lost.entity.Post;
>>>>>>> 04808687a500286a6c3539bc63b25f7eb0402c1e
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

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    private List<Post> posts;

    public User update(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        return this;
    }
}

