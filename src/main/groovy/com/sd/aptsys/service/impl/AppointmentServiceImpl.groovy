package com.sd.aptsys.service.impl

import com.sd.aptsys.entity.Appointment
import com.sd.aptsys.repository.AppointmentRepository
import com.sd.aptsys.service.AppointmentService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository
    private static final Logger log = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository
    }

    @Override
    boolean delete(Appointment appointment) {
        try {
            appointmentRepository.delete(appointment)
            log.info("successfully deleted appointment with id ${appointment.id}")
            return true
        } catch(Exception e) {
            log.error("Error deleting appointment ${e.message}")
            return false
        }
    }

    @Override
    Appointment save(Appointment appointment) {
        appointmentRepository.save(appointment)
    }

    @Override
    List<Appointment> findAll() {
       appointmentRepository.findAll() as List
    }

    @Override
    Appointment findById(Long id) {
        final Optional<Appointment> appointment = appointmentRepository.findById(id)
        return appointment.present ? appointment.get() : null
    }

}
