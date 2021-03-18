package com.sd.aptsys.service

import com.sd.aptsys.entity.User
import com.sd.aptsys.service.base.Eraseable
import com.sd.aptsys.service.base.Savable

interface UserService extends Savable<User>, Eraseable<User> {
    User findByUsername(String username)
    User findByLatestToken(String token)
    List<User> findAll()
}
