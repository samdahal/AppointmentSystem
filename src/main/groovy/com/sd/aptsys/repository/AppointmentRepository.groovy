package com.sd.aptsys.repository

import com.sd.aptsys.entity.Appointment
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    //@Query("SELECT a FROM Appointment a WHERE a.user.username=:username AND a.business.com_sd_aptsys_entity_trait_HasIdentity__id=:businessDivisionCode")
    //List<Appointment> findByUsername(@Param('businessDivisionCode') Long businessDivisionCode, @Param('username') String username)
    List<Appointment> findByUserUsername(String username)
}
