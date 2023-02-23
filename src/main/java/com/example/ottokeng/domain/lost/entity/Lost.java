package com.example.ottokeng.domain.lost.entity;

import com.example.ottokeng.domain.find.entity.FindWriting;
import com.example.ottokeng.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Lost {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "lost")
    private List<LostWriting> lostWriting;

    @OneToOne(mappedBy = "lost")
    private User user;
}
