package com.web.project.springboot.config.auth.dto;

import com.web.project.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;


    /*기존 User클래스는 직렬화를 구현해야함, 엔티티로서 많이쓰이는걸 직렬화처리하면 성능이슈가 발생할수있음*/
    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }


}
