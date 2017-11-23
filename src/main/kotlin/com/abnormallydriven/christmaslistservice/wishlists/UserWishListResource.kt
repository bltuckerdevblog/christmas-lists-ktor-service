package com.abnormallydriven.christmaslistservice.wishlists

import com.abnormallydriven.christmaslistservice.UserWishListResource
import com.abnormallydriven.christmaslistservice.delete
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.response.respond
import io.ktor.routing.Route

fun Route.userWishListResource(){
    get<UserWishListResource>{
        call.respond(HttpStatusCode.NotImplemented, "{items: []}")

    }
    post<UserWishListResource>{
        call.respond(HttpStatusCode.NotImplemented, "{name : \"item name\"}")
    }
    delete<UserWishListResource>{
        call.respond(HttpStatusCode.NotImplemented, "")
    }

}