package com.example.app_for_task.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {

        Set<String> perm = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (perm.contains("tasks:create")) {
            httpServletResponse.sendRedirect("/admin/home");
        } else {
            httpServletResponse.sendRedirect("/employee/home");
        }
    }
}