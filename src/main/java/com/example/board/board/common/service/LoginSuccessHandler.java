package com.example.board.board.common.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        SecurityContextHolder -> SecurityContext -> Authentication
        HttpSession httpSession = request.getSession();
        String role = null;
        for (GrantedAuthority g : authentication.getAuthorities()) {
            role = g.getAuthority();
        }
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        httpSession.setAttribute("role", role);
        httpSession.setAttribute("email", authentication.getName());
        response.sendRedirect("/");
    }
}
