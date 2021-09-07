package com.web.project.springboot.config.auth;

import com.web.project.springboot.config.auth.dto.LoginUser;
import com.web.project.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component  //조건에 맞는 경우 메소드가 있다면 구현체가 지정한값으로 해당 메소드로 값을 넘김
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter){

        //컨트롤러 메서드의 특정 파라미터를 지원하는지 판단, 여기서는 @LoginUser 어노테이션이 붙어있고 파라미터 클래스타입이 SessionUser.class인경우 true를 반환
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;

        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        return isLoginUserAnnotation && isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception{
        //파라미터에 전달할 객체를 생성
        return httpSession.getAttribute("user");
    }


}
