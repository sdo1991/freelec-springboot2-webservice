package com.dongoh.book.springboot.config.auth;

import com.dongoh.book.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //컨트롤러 메소드의 특정 파라미터의 지원여부 판단
        //@LoginUser어노테이션이 붙어있고, SessionUser.class가 파라미터 클래스 타입이면 true 리턴
        //(@LoginUser SessionUser param)인 경우에 트루, resolveArgument()실행?
        boolean isLoginUserAnnotation=parameter.getParameterAnnotation(LoginUser.class)!=null;
        boolean isUserClass= SessionUser.class.equals(parameter.getParameterType());

        return isLoginUserAnnotation&&isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //파라메터에 전달할 객체 생성
        //여기서는 세션에 객체를 가져옴
        return httpSession.getAttribute("user");
    }
}
