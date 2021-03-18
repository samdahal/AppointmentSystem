package com.sd.aptsys.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class AuthenticationToken extends UsernamePasswordAuthenticationToken {

    String token

    AuthenticationToken(String token) {
        super(null, null)
        this.token = token
    }

    @Override
    Object getCredentials() {
        return null
    }

    @Override
    Object getPrincipal() {
        return null
    }
}
