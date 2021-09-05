package com.web.project.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    /*스프링 시큐리티 권한코드에 항상 ROLE_이 앞에 있어야만합니다*/
    GUEST("ROLE_GUEST","손님"),
    USER("ROLE_USER", "일반사용자");

    private final String key;
    private final  String title;

}
