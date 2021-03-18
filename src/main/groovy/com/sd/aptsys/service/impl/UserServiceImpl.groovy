package com.sd.aptsys.service.impl

import com.sd.aptsys.entity.User
import com.sd.aptsys.repository.UserRepository
import com.sd.aptsys.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl implements UserService {

    private final UserRepository userRepository

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    @Override
    boolean delete(User user) {
        return false
    }

    @Override
    User findByUsername(String username) {
        userRepository.findByUsername(username)
    }

    @Override
    User save(User user) {
        userRepository.save(user)
    }

    @Override
    User findByLatestToken(String token) {
        userRepository.findByLastestToken(token)
    }

    @Override
    List<User> findAll() {
        userRepository.findAll() as List
    }
}
