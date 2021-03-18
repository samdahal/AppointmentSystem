package com.sd.aptsys.security

import groovy.json.JsonBuilder
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.contentType = 'application/json'
        httpServletResponse.writer.write(new JsonBuilder([status: 'error', reason: 'unauthorized']).toString())
        httpServletResponse.writer.flush()
    }
}
