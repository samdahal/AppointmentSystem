package com.sd.aptsys.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MyAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    MyAuthenticationTokenFilter() {
        super("/v1/api/**")
    }

    @Override
    Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        AuthenticationToken token = new AuthenticationToken(authorizationHeader);
        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult)
        chain.doFilter(request, response)
    }
}
