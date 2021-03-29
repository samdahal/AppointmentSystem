package com.sd.aptsys.front.domain

import com.sd.aptsys.validator.RequiredField


class SignupRequest {

    @RequiredField(message = "firstname cannot be null/empty")
    String firstname

    @RequiredField(message = "lastname cannot be null/empty")
    String lastname

    @RequiredField(message = "Username cannot be null/empty")
    String username

    @RequiredField(message = "password cannot be null/empty")
    String password
}
