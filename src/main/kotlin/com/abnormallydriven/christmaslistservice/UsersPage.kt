package com.abnormallydriven.christmaslistservice

import com.abnormallydriven.christmaslistservice.users.UserDao
import com.abnormallydriven.christmaslistservice.wishlists.WishListDao
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route


fun Route.userPage(userDao: UserDao, wishListDao: WishListDao){
    get<UserPage>{
        call.respond(FreeMarkerContent("index.ftl", mapOf("model" to IndexPageModel(userDao.getAllUsers()))))
    }

    post<UserPage> {
        val formParams = call.receiveParameters()

        if(formParams["name"] != null && formParams["isNice"] != null){
            //success
            val createdUser = userDao.createUser(formParams["name"]!!, formParams["isNice"]!!.toBoolean())
            wishListDao.createChristmasWishList(createdUser.id)
            call.respond(FreeMarkerContent("index.ftl", mapOf("model" to IndexPageModel(userDao.getAllUsers()))))
        } else {
            call.respond(FreeMarkerContent("index.ftl", mapOf("model" to IndexPageModel(userDao.getAllUsers(), "Invalid user data entered"))))
        }

    }

}