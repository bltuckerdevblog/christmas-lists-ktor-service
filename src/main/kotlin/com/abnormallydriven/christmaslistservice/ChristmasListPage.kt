package com.abnormallydriven.christmaslistservice

import com.abnormallydriven.christmaslistservice.users.UserDao
import com.abnormallydriven.christmaslistservice.wishlists.WishListDao
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route


fun Route.christmasListPage(userDao: UserDao, wishListDao: WishListDao) {
    get<ChristmasListPage> { christmasListPage ->
        val user = userDao.getUserById(christmasListPage.userId)
        val christmasList = wishListDao.getChristmasWishList(christmasListPage.userId)

        call.respond(FreeMarkerContent("christmas_list_page.ftl", mapOf("model" to ChristmasListPageModel(user!!, christmasList!!))))
    }

    post<ChristmasListPage> { christmasListPage ->
        val itemTitle = call.receiveParameters()["title"]
        val user = userDao.getUserById(christmasListPage.userId)

        if(user == null){
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        if (itemTitle == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        val christmasList = wishListDao.getChristmasWishList(christmasListPage.userId)

        if (christmasList == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        wishListDao.addItem(christmasList.id, itemTitle)
        call.respond(FreeMarkerContent("christmas_list_page.ftl", mapOf("model" to ChristmasListPageModel(user, christmasList))))
    }

}