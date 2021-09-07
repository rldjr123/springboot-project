package com.web.project.springboot.config.auth.dto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {

    //Target은 어노테이션이 생성될수있는 위치를 지정, 파라미터로 지정했으니 메소드의 파라미터로 선언된 객체만 사용할수있음, 이외에도 타입이존재함
    //@interface는 이파일을 어노테이션클래스로 지정함, LoginUser라는 이름을 가진 어노테이션을생성
    //@Retention는 어노테이션의 생명주기 설정 (RunTime, class, Source) 좀더 파악해봐야할듯

}
