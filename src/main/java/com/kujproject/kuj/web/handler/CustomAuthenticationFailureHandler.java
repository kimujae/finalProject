package com.kujproject.kuj.web.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, AuthenticationException exception) throws IOException, jakarta.servlet.ServletException {
        // 로그인 실패 시 원하는 동작을 수행하는 로직을 여기에 작성합니다.
        // 예를 들어, 에러 메시지를 설정하거나 다른 페이지로 리다이렉트할 수 있습니다.

        // 예시: 에러 메시지를 설정하고 로그인 페이지로 다시 리다이렉트

        String errorMessage = "";

        if (exception instanceof BadCredentialsException) {
            errorMessage = "아이디 또는 비밀번호가 잘못되었습니다.";
            System.out.println(1);
        } else if (exception instanceof DisabledException) {
            errorMessage = "계정이 비활성화되었습니다.";
            System.out.println(2);
        } else if (exception instanceof LockedException) {
            errorMessage = "계정이 잠겼습니다. 잠시 후 다시 시도해주세요.";
        } else if (exception instanceof CredentialsExpiredException) {
            errorMessage = "비밀번호가 만료되었습니다. 비밀번호를 변경해주세요.";
        } else {
            errorMessage = "로그인에 실패하였습니다.";
            System.out.println(3);
        }
        request.getSession().setAttribute("error", "로그인에 실패하였습니다. 다시 시도해주세요.");
        response.sendRedirect("/loginform");
    }
}