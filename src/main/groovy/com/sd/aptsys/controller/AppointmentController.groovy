package com.sd.aptsys.controller

import com.sd.aptsys.entity.Appointment
import com.sd.aptsys.service.AppointmentService
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.validation.Valid

@CompileStatic
@RestController
@RequestMapping("/v1/api")
class AppointmentController {

    final AppointmentService appointmentService

    @Autowired
    AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService
    }

    @GetMapping("/appointment/all")
    def findAllAppointment() {
        return appointmentService.findAll()
    }

    @GetMapping("/appointment/mine")
    def findMyAppointments() {
        return appointmentService.findMyAppointments()
    }

    @GetMapping("/appointment/{id}")
    def findAppointmentById(@PathVariable('id') Long id) {
        return appointmentService.findById(id)
    }

    @GetMapping("/appointment/cancel/{id}")
    def cancelAppointment(@PathVariable('id') Long id) {
        Appointment appointment = appointmentService.findById(id)
        appointment.active = false
        appointment = appointmentService.save(appointment)
        return  appointment
    }

    @PutMapping("/appointment/update")
    def updateAppointment(@Valid @RequestBody Appointment appointment) {
        if (appointmentService.findById(appointment.id) == null) {
            return [status: 'error', msg: 'Invalid appointment id/appointment doesnt exist']
        }
        return appointmentService.save(appointment)
    }

    @PostMapping("/appointment/create")
    def createAppointment(@Valid @RequestBody Appointment appointment) {
        return appointmentService.save(appointment)
    }

    @DeleteMapping('/appointment/delete/{id}')
    def deleteAppointment(@PathVariable('id') long id) {
        Appointment appointment = appointmentService.findById(id)
        if (!appointment) {
            return [status: 'error', msg: 'Invalid Id...couldnt be deleted']
        }
        boolean result = appointmentService.delete(appointment)
        return [status: 'success', msg: "successfully deleted appoint with id ${appointment.id}".toString()]

    }



}
