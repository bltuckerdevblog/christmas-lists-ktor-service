package com.abnormallydriven.christmaslistservice.wishlists

import com.abnormallydriven.christmaslistservice.UserWishListsResource
import com.abnormallydriven.christmaslistservice.delete
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.locations.put
import io.ktor.response.respond
import io.ktor.routing.Route

fun Route.userWishListsResource(){
    get<UserWishListsResource>{
        call.respond(HttpStatusCode.NotImplemented, "{wishlists: []}")
    }

    put<UserWishListsResource>{
        call.respond(HttpStatusCode.NotImplemented, "")
    }

    post<UserWishListsResource>{
        call.respond(HttpStatusCode.NotImplemented, "{name: \"WishListName\", items: []}")
    }

    delete<UserWishListsResource>{
        call.respond(HttpStatusCode.NotImplemented, "")
    }
}