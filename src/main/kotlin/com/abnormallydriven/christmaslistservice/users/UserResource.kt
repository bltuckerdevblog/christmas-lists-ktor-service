package com.abnormallydriven.christmaslistservice.users

import com.abnormallydriven.christmaslistservice.UserResource
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.get
import io.ktor.locations.put
import io.ktor.response.respond
import io.ktor.routing.Route

fun Route.userResource(){
    get<UserResource>{
        call.respond(HttpStatusCode.NotImplemented, "{userId: 1, name: \"Somebody\"}")

    }

    put<UserResource>{
        call.respond(HttpStatusCode.NotImplemented, "")
    }
}