package com.sd.aptsys.service

import com.sd.aptsys.entity.Appointment
import com.sd.aptsys.entity.User
import com.sd.aptsys.service.base.Eraseable
import com.sd.aptsys.service.base.Savable

interface AppointmentService extends Savable<Appointment>, Eraseable<Appointment> {
    List<Appointment> findAll()
    List<Appointment> findMyAppointments();
    Appointment findById(Long id)
    Appointment save(Appointment appointment)
}
