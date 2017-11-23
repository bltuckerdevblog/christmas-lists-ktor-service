package com.abnormallydriven.christmaslistservice.users

import com.abnormallydriven.christmaslistservice.UsersResource
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.locations.*

fun Route.usersResource(){
    get<UsersResource> {
        call.respond(HttpStatusCode.NotImplemented, "{usersResource: []}")
    }
    post<UsersResource> {
        call.respond(HttpStatusCode.NotImplemented, "")
    }

}