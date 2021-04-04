package com.sd.aptsys.controller

import com.sd.aptsys.entity.User
import com.sd.aptsys.front.domain.LoginRequest
import com.sd.aptsys.front.domain.SignupRequest
import com.sd.aptsys.security.MyUserDetails
import com.sd.aptsys.service.UserService
import groovy.transform.CompileStatic
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid

@CompileStatic
@RestController
@RequestMapping("/auth")
class AuthController {

    private final UserService userService
    private final PasswordEncoder passwordEncoder

    AuthController(final UserService userService, final PasswordEncoder passwordEncoder) {
        this.userService = userService
        this.passwordEncoder = passwordEncoder
    }


    @GetMapping('/login')
    def login() {
        return [msg: 'Get is not supported. Use post to login']
    }

    @PostMapping('/login')
    def processLogin(@Valid @RequestBody LoginRequest loginRequest) {
        User user = userService.findByUsername(loginRequest.username)
        if (!user) {
            return [status: 'error', reason: 'username_password_invalid']
        }

        if (user.banned) {
            return [status: 'error', reason: 'user_banned']
        }

        if (user.locked) {
            return [status: 'error', reason: 'user_locked']
        }

        boolean passwordMatch = passwordEncoder.matches(loginRequest.getPassword(), user.getHashedPassword())

        if (!passwordMatch) {
            return [status: 'error', reason: 'username_password_invalid']
        }

        List<GrantedAuthority> authorities = []
        user.roles.each {authorities << new SimpleGrantedAuthority(it.role)}

        MyUserDetails myUserDetails = new MyUserDetails(id: user.id, username: user.username, authorities: authorities)
        String token = myUserDetails.generateToken()
        user.lastestToken = token
        userService.save(user)

        return [status: 'success', userId: user.id, username: user.username, roles: user.roles, token: token]
    }

    @PostMapping('/isAuthenticated')
    def isAuthenticatedCheck(@RequestBody Map<String, String> data) {
        println "Hello"
        return [status: true, isAuthValid: false]
    }

    @PostMapping('/signup')
    def processSignup(@Valid @RequestBody SignupRequest signUpRequest) {
        User user = userService.findByUsername(signUpRequest.username)
        if (user) {
            return [status: 'error', reason: 'user_exists']
        }
        User toSave = new User()
        toSave.firstName = signUpRequest.firstname
        toSave.lastName = signUpRequest.lastname
        toSave.username = signUpRequest.username
        toSave.hashedPassword = passwordEncoder.encode(signUpRequest.password)
        toSave = userService.save(toSave)
        return [status: 'success', user: toSave]
    }

}
