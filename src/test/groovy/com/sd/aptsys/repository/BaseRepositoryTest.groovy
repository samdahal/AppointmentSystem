package com.sd.aptsys.repository

import com.sd.aptsys.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest


@AutoConfigureTestDatabase
@SpringBootTest
class BaseRepositoryTest {

    @Autowired
    protected final AppointmentRepository  appointmentRepository

/*    @Autowired
    protected final BusinessRepository businessRepository*/

    @Autowired
    protected final UserRepository userRepository

    protected User createTestUser(Map info = null) {
        User user = new User(firstName: 'Sam', lastName: 'Dahal', username: 'samdahal2', hashedPassword: '2fd1ere5w1e1fsaq3w333535r44')
        if (info) {
            info.each {k, v -> user."$k" = v }
        }
        userRepository.save(user)
    }
/*
    protected Business createTestBusiness(Map info = null) {
        Business business = new Business(name: 'Naira Salon')
        if (info) {
            info.each {k, v -> business."$k" = v}
        }
         businessRepository.save(business)
    }*/

}
