package com.sd.aptsys.security

import com.sd.aptsys.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails

@Configuration
class MyAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private static final Logger log = LoggerFactory.getLogger(MyAuthenticationProvider.class)

    @Autowired
    final UserService userService

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String s, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        AuthenticationToken authenticationToken = usernamePasswordAuthenticationToken as AuthenticationToken
        log.info("attempting to authenticate user with token --> ${authenticationToken.token}")
        assert userService != null
        if (!authenticationToken.token) {
            return new MyUserDetails(-1L, null, null, [])
        }
        return MyUserDetails.fromToken(authenticationToken.token, userService.findByLatestToken(authenticationToken.token))
    }
}
