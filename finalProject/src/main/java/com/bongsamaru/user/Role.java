package com.bongsamaru.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_M04", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;

}