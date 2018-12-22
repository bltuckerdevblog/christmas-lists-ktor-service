package com.abnormallydriven.christmaslistservice.users

import com.abnormallydriven.christmaslistservice.UsersResource
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.locations.*
import io.ktor.request.receive

fun Route.usersResource(userDao: UserDao){
    get<UsersResource> {
        call.respond(userDao.getAllUsers())
    }
    post<UsersResource> {
        val dto = call.receive<User>()

        val createdUser = userDao.createUser(dto.name, dto.isNice)

        call.respond(createdUser)
    }

}