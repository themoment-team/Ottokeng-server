package com.example.ottokeng.domain.find.entity;

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
public class Find {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "find")
    private List<FindWriting> findWriting;

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "find")
    private User user;
}
