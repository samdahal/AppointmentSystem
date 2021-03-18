package com.sd.aptsys.repository

import com.sd.aptsys.entity.Appointment
import com.sd.aptsys.entity.User
import org.apache.commons.lang3.time.DateUtils
import org.junit.jupiter.api.Test

class AppointmentRepositoryTest extends BaseRepositoryTest {

    @Test
    void contextLoads() {
        assert appointmentRepository != null
        assert  businessRepository != null
    }

    @Test
    void saveShouldSaveAnAppointment() {
     /*   // Setup data

        // Appointment belongs to business so the business is needed to create an appointment
        Business business = new Business(name: 'Naira Salon')
        Business savedBusiness = businessRepository.save(business)

        Appointment appointment = new Appointment()
        appointment.business = savedBusiness
        appointment.title = 'My Test appointment'
        appointment.start = new GregorianCalendar(2021, 2, 5, 11, 30).time
        appointment.end = DateUtils.addMinutes(appointment.start, 30)

        // Call the method
        Appointment persisted = appointmentRepository.save(appointment)

        // Asserts
        assert persisted != null
        Appointment byId = appointmentRepository.findById(persisted.id).get()
        assert byId != null*/
    }

    @Test
    void findByUsernameTest() {
/*        User testUser = createTestUser()
        assert testUser != null

        User testUser1 = createTestUser([username: 'rsharma2'])
        assert testUser1 != null
        assert testUser1.username == 'rsharma2'

        Business testBusiness = createTestBusiness()
        assert testBusiness != null

        Business testBusiness1 = createTestBusiness([name: 'Sams Office'])
        assert  testBusiness1 != null

        Appointment appointment1 = new Appointment(title: 'Doctor Appointment', user: testUser, business: testBusiness)
        Appointment appointment2 = new Appointment(title: 'Dentist Appointment', user: testUser, business: testBusiness)
        Appointment appointment3 = new Appointment(title: 'Dormetology Appointment', user: testUser1, business: testBusiness)
        Appointment appointment4 = new Appointment(title: 'Dormetology Appointment', user: testUser1, business: testBusiness1)

        List<Appointment> saved = appointmentRepository.saveAll(Arrays.asList(appointment1, appointment2, appointment3, appointment4))
        assert saved.size() == 4

        List<Appointment> findByUser = appointmentRepository.findByUsername(testBusiness.id, testUser.username)
        assert findByUser.size() == 2*/
    }


}
