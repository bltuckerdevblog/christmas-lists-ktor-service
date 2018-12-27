package com.abnormallydriven.christmaslistservice

import com.abnormallydriven.christmaslistservice.users.UserDao
import com.abnormallydriven.christmaslistservice.users.userResource
import com.abnormallydriven.christmaslistservice.users.usersResource
import com.abnormallydriven.christmaslistservice.wishlists.WishListDao
import com.abnormallydriven.christmaslistservice.wishlists.userWishListResource
import com.abnormallydriven.christmaslistservice.wishlists.userWishListsResource
import freemarker.cache.ClassTemplateLoader
import io.ktor.application.install
import io.ktor.features.*
import io.ktor.freemarker.FreeMarker
import io.ktor.gson.gson
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.Locations
import io.ktor.routing.Routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

@Location("/")
class UserPage()

@Location("/users/{userId}/christmaslist")
data class ChristmasListPage(val userId: Long)

@Location("/users")
class UsersResource()

@Location("/users/{userId}")
data class UserResource(val userId: Long)

@Location("/users/{userId}/wishlists")
data class UserWishListsResource(val userId: Long)

@Location("/users/{userId}/wishlist/{wishlistId}")
data class UserWishListResource(val userId: Long, val wishlistId: Long)


@KtorExperimentalLocationsAPI
fun main(args: Array<String>) {
    embeddedServer(Netty, 8080){
        val userDao = UserDao()
        val wishListDao = WishListDao(userDao)


        install(DefaultHeaders){
            header("Seasonal-Greeting", "Merry Christmas")
        }
        install(ContentNegotiation){
            gson{
                setPrettyPrinting()
            }
        }
        install(FreeMarker){
            templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
        }
        install(CORS)
        install(CallLogging)
        install(Compression)
        install(Locations)
        install(Routing) {

            userPage(userDao, wishListDao)
            christmasListPage(userDao, wishListDao)
            usersResource(userDao)
            userResource(userDao)
            userWishListsResource(wishListDao)
            userWishListResource(wishListDao)
        }
    }.start(true)
}
