package com.abnormallydriven.christmaslistservice.users

import com.abnormallydriven.christmaslistservice.UserResource
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.get
import io.ktor.locations.put
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route

fun Route.userResource(userDao :UserDao){
    get<UserResource>{
        val userId : Long? = call.parameters["userId"]?.toLong()
        if(userId != null){
            val user = userDao.getUserById(userId)
            if(user != null){
                call.respond(user)
                return@get
            }
        }

        call.respond(HttpStatusCode.BadRequest, "No user exists for the given id")
    }

    put<UserResource>{
        val dto = call.receive<User>()

        val userById = userDao.getUserById(dto.id)

        if(userById == null){
            call.respond(HttpStatusCode.BadRequest, "No user exists for that id")
            return@put
        }

        userDao.updateUser(dto)

        call.respond(dto)

    }
}