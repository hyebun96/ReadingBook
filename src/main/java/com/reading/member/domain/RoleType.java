package com.reading.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {

    ADMIN ("관리자"),
    DEFAULT_USER ("일반사용자"),
    SOCIAL_LINKED_USER ("소셜 연동 사용자");

    private final String role;

}
