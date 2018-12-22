package com.abnormallydriven.christmaslistservice

import com.abnormallydriven.christmaslistservice.users.User
import com.abnormallydriven.christmaslistservice.users.UserDao
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post


fun Route.userPage(userDao: UserDao){
    get{
        call.respond(FreeMarkerContent("index.ftl", mapOf("model" to IndexPageModel(listOf()))))
    }

    post {
        val formParams = call.receiveParameters()

        if(formParams["name"] != null && formParams["isNice"] != null){
            //success
            call.respond(FreeMarkerContent("index.ftl", mapOf("model" to IndexPageModel(listOf(User(1L, formParams["name"]!!, formParams["isNice"]!!.toBoolean()))))))
        } else {
            call.respond(FreeMarkerContent("index.ftl", mapOf("model" to IndexPageModel(listOf(), "Invalid user data entered"))))
        }

    }

}