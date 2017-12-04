package com.abnormallydriven.christmaslistservice.wishlists

import com.abnormallydriven.christmaslistservice.UserWishListsResource
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route

fun Route.userWishListsResource(wishListDao: WishListDao){
    get<UserWishListsResource>{
        val userId : Long? = call.parameters["userId"]?.toLong()

        if(userId == null){
            call.respond(HttpStatusCode.BadRequest, "No user exists for the given id")
        }

        val userWishLists = wishListDao.getUserWishLists(userId!!)
        call.respond(userWishLists)
    }

    post<UserWishListsResource>{

        val userId : Long? = call.parameters["userId"]?.toLong()

        if(userId == null){
            call.respond(HttpStatusCode.BadRequest, "No user exists for the given id")
        }

        val createWishListDto = call.receive<CreateWishListDto>()

        val createdWishList = wishListDao.createWishList(createWishListDto.name, userId!!)

        call.respond(createdWishList)
    }
}