package com.sd.aptsys.entity

import com.sd.aptsys.validator.RequiredField
import org.springframework.data.mongodb.core.mapping.Field

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    @RequiredField( message = 'Start date is required')
    Date start

   // @RequiredField(message = 'End date is required')
    Date end

    @RequiredField(message = 'Title is required')
    String title

    @ManyToOne
    @JoinColumn(name = 'user_id')
    User user

    boolean active = true

    Date createDate = new GregorianCalendar().time
    Date updateDate = new GregorianCalendar().time
}




