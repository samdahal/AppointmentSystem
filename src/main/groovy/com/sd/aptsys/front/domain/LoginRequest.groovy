package com.sd.aptsys.front.domain

import com.sd.aptsys.validator.RequiredField


class LoginRequest {

    @RequiredField(message = "Username cannot be null/empty")
    String username

    @RequiredField(message = "password cannot be null/empty")
    String password
}
