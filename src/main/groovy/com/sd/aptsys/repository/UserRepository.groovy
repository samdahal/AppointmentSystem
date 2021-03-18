package com.sd.aptsys.repository

import com.sd.aptsys.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository extends CrudRepository<User, Long> {
/*    @Query("SELECT a FROM Appointment a WHERE a.user.username=:username")
    List<Appointment> findByUsername(@Param('username') String username)*/

    User findByUsername(String username)
    User findByLastestToken(String token)
}
