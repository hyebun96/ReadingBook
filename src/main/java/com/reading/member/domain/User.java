package com.reading.member.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String pwd;

    @Column(nullable = false)
    private String profile_image_url;

}
