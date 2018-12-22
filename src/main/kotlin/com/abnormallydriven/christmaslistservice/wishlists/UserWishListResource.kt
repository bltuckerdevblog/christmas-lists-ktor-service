package com.abnormallydriven.christmaslistservice.wishlists

import com.abnormallydriven.christmaslistservice.UserWishListResource
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.locations.delete
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route

fun Route.userWishListResource(wishListDao: WishListDao){
    get<UserWishListResource>{

        val wishListId : Long? = call.parameters["wishlistId"]?.toLong()

        if(wishListId == null){
            call.respond(HttpStatusCode.BadRequest, "No wishlist exists for the given id")
        }

        val wishListById = wishListDao.getWishListById(wishListId!!)

        if(wishListById == null){
            call.respond(HttpStatusCode.BadRequest, "No wishlist exists for the given id")
        }

        call.respond(wishListById!!)

    }

    post<UserWishListResource>{
        val wishListId : Long? = call.parameters["wishlistId"]?.toLong()

        if(wishListId == null){
            call.respond(HttpStatusCode.BadRequest, "No wishlist exists for the given id")
        }

        val wishListById = wishListDao.getWishListById(wishListId!!)

        if(wishListById == null){
            call.respond(HttpStatusCode.BadRequest, "No wishlist exists for the given id")
        }

        val item = call.receive<WishListItem>()

        if(item == null){
            call.respond(HttpStatusCode.BadRequest, "Please provide a valid item to add")
        }

        wishListDao.addItem(wishListId!!, item!!.title)

        call.respond(HttpStatusCode.NoContent)

    }

    delete<UserWishListResource>{
        val wishListId : Long? = call.parameters["wishlistId"]?.toLong()

        if(wishListId == null){
            call.respond(HttpStatusCode.BadRequest, "No wishlist exists for the given id")
        }

        val wishListById = wishListDao.getWishListById(wishListId!!)

        if(wishListById == null){
            call.respond(HttpStatusCode.BadRequest, "No wishlist exists for the given id")
        }

        val item = call.receive<WishListItem>()

        if(item == null){
            call.respond(HttpStatusCode.BadRequest, "Please provide a valid item to remove")
        }

        wishListDao.removeItem(wishListId!!, item!!.title)

        call.respond(HttpStatusCode.NoContent)
    }

}