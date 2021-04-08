package com.sd.aptsys.controller

import com.sd.aptsys.security.AuthenticationToken
import com.sd.aptsys.security.MyUserDetails
import com.sd.aptsys.service.UserService
import groovy.transform.CompileStatic
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.security.Principal

@CompileStatic
@RestController
@RequestMapping("/v1/api/user")
class UserController {

    private final AuthenticationManager authenticationManager
    private final UserService userService

    UserController(final AuthenticationManager authenticationManager, UserService userService) {
        this.userService = userService
        this.authenticationManager = authenticationManager
    }

    @GetMapping("/getCurrentUser")
    def getCurrentUser(Principal principal) {
        userService.findByUsername(principal.name)
    }

    @GetMapping('/isAuthenticated')
    def isAuthenticatedCheck(Principal principal) {
        /**
         * Auth valid is checked by trying to attempt the authenticated token with spring and get the principle
         * is princle is myuserdetail then it is valid user not otherwise
         */
        boolean isAuthValid = principal.name != null
        return [status: true, isAuthValid: isAuthValid]
    }
}
