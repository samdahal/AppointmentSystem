package com.sd.aptsys.security

import groovy.json.JsonBuilder
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.contentType = 'application/json'
        httpServletResponse.writer.write(new JsonBuilder([status: 'error', reason: 'access_denied']).toString())
        httpServletResponse.writer.flush()
    }

}
