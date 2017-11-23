package com.abnormallydriven.christmaslistservice

import com.abnormallydriven.christmaslistservice.users.userResource
import com.abnormallydriven.christmaslistservice.users.usersResource
import com.abnormallydriven.christmaslistservice.wishlists.userWishListResource
import com.abnormallydriven.christmaslistservice.wishlists.userWishListsResource
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.Compression
import io.ktor.features.DefaultHeaders
import io.ktor.http.HttpMethod
import io.ktor.locations.Locations
import io.ktor.locations.handle
import io.ktor.locations.location
import io.ktor.pipeline.PipelineContext
import io.ktor.routing.Route
import io.ktor.routing.Routing
import io.ktor.routing.method
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

@location("/users")
class UsersResource()

@location("/users/{userId}")
class UserResource(val userId: Long)

@location("/users/{userId}/wishlists")
class UserWishListsResource(val userId: Long)

@location("/users/{userId}/wishlist/{wishlistId}")
class UserWishListResource(val userId: Long, val wishlistId: Long)


fun Application.module() {

    install(DefaultHeaders){
        header("Seasonal-Greeting", "Merry Christmas")
    }
    install(CORS)
    install(CallLogging)
    install(Compression)
    install(Locations)
    install(Routing) {
        usersResource()
        userResource()
        userWishListsResource()
        userWishListResource()
    }

}

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080, watchPaths = listOf("ApplicationKt"), module = Application::module).start()
}

//Needed because the locations feature doesnt do this yet.
inline fun <reified T : Any> Route.delete(noinline body: suspend PipelineContext<Unit, ApplicationCall>.(T) -> Unit): Route {
    return location(T::class) {
        method(HttpMethod.Delete) {
            handle(body)
        }
    }
}