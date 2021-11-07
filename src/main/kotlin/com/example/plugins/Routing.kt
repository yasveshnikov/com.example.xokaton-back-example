package com.example.plugins

import com.example.cats.CatClient
import com.example.yandex.YandexClient
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureRouting() {

    val client = CatClient()
    val yandexClient = YandexClient()

    install(Locations) {
    }

    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/fact") {
            val fact = client.getFact()
            call.respondText(fact!!)
        }
        get("/near_station") {
            val lat = 59.9386f
            val lng = 30.3141f
            val responseBody=yandexClient.getNearStation(lat, lng)
            call.respondText(responseBody.toString())
        }
        install(StatusPages) {
            exception<AuthenticationException> { cause ->
                call.respond(HttpStatusCode.Unauthorized)
            }
            exception<AuthorizationException> { cause ->
                call.respond(HttpStatusCode.Forbidden)
            }

        }
        get<MyLocation> {
            call.respondText("Location: name=${it.name}, arg1=${it.arg1}, arg2=${it.arg2}")
        }
        // Register nested routes
        get<Type.Edit> {
            call.respondText("Inside $it")
        }
        get<Type.List> {
            call.respondText("Inside $it")
        }
    }
}

class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

@Location("/location/{name}")
class MyLocation(val name: String, val arg1: Int = 42, val arg2: String = "default")

@Location("/type/{name}")
data class Type(val name: String) {
    @Location("/edit")
    data class Edit(val type: Type)

    @Location("/list/{page}")
    data class List(val type: Type, val page: Int)
}
