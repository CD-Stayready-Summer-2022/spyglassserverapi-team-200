package com.team200.spyglassserver.com.team200.spyglassserver.domain;

import com.team200.spyglassserver.security.firebase.models.FireBaseUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PrincipalDetailsArgumentResolver implements HandlerMethodArgumentResolver {
    private FireBaseUser fireBaseUser;
    public PrincipalDetailsArgumentResolver(FireBaseUser user){
        this.fireBaseUser = user;
    }
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(FireBaseUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return fireBaseUser;
    }
}
