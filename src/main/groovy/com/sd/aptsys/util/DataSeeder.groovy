package com.sd.aptsys.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.sd.aptsys.entity.Appointment
import com.sd.aptsys.entity.User
import com.sd.aptsys.service.AppointmentService
import com.sd.aptsys.service.UserService
import groovy.transform.CompileStatic
import org.apache.commons.lang3.time.DateUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder


class DataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class)

    final UserService userService
    final PasswordEncoder passwordEncoder
    final AppointmentService appointmentService

    DataSeeder(UserService userService, PasswordEncoder passwordEncoder, AppointmentService appointmentService) {
        this.userService = userService
        this.passwordEncoder = passwordEncoder
        this.appointmentService = appointmentService
    }

    @Override
    void run(String... args) throws Exception {

        // Save the data to database
        List<User> persistedUsers = userService.findAll()

        getUsersList().each {user ->
           // persistedUsers << userService.save(user)
            boolean exist = persistedUsers?.contains(user)
            if (!exist) {
                userService.save(user)
            }
        }

        Calendar cal = new GregorianCalendar(2021, 3, 5, 11, 30)
        Date end = DateUtils.addMinutes(cal.time, 20)
        Appointment appointment = new Appointment( user: persistedUsers.get(0), title: 'Doctor Visit', start: cal.time, end: end)
        Appointment appointment1 = appointmentService.save(appointment)

    }

    private List<Appointment> getAppointmentDataList() {
        List<Appointment> appointmentList = []

    }

    private List<User> getUsersList() {
        List<User> userList = []
        userList << new User(
                firstName: 'Sam',
                lastName: 'Dahal',
                username: 'samdahal2',
                createDate: new Date(),
                locked: false,
                hashedPassword: passwordEncoder.encode('secret')
        )

        userList << new User(
                firstName: 'Raj',
                lastName: 'Sharma',
                username: 'rsharma',
                createDate: new GregorianCalendar(2021, 0, 31, 11, 20, 12).time,
                locked: false,
                hashedPassword: passwordEncoder.encode('secret123')
        )

        userList << new User(
                firstName: 'Joshua',
                lastName: 'Anderson',
                username: 'jander3',
                banned: true,
                createDate: new GregorianCalendar(2021, 2, 31, 11, 20, 12).time,
                locked: false,
                hashedPassword: passwordEncoder.encode('jander3secret')
        )

        userList << new User(
                firstName: 'Bhagat',
                lastName: 'Rizal',
                username: 'brizal3',
                createDate: new GregorianCalendar(2020, 11, 31, 11, 20, 12).time,
                locked: false,
                hashedPassword: passwordEncoder.encode('brizalpassword')
        )

        userList << new User(
                firstName: 'Tek',
                lastName: 'Dahal',
                username: 'tdahal',
                createDate: new GregorianCalendar(2020, 11, 31, 11, 20, 12).time,
                locked: false,
                hashedPassword: passwordEncoder.encode('secret')
        )

        userList << new User(
                firstName: 'Laxmi',
                lastName: 'Poudyel',
                username: 'lpoudyel',
                createDate: new GregorianCalendar(2020, 11, 31, 11, 20, 12).time,
                locked: false,
                hashedPassword: passwordEncoder.encode('secret')
        )


        return userList
    }
}
