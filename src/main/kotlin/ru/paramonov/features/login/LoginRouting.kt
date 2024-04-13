package ru.paramonov.features.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.paramonov.features.cache.InMemoryCache
import ru.paramonov.features.cache.TokensCache
import java.util.*

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            val receive = call.receive<LoginReceiveRemote>()
            val firstLogin = InMemoryCache.users.firstOrNull { it.username == receive.username }

            firstLogin?.let {
                if (firstLogin.password == receive.password) {
                    val token = UUID.randomUUID().toString()
                    InMemoryCache.tokens.add(TokensCache(username = receive.username, token = token))
                    call.respond(message = LoginResponseRemote(token = token))
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            } ?: call.respond(HttpStatusCode.BadRequest, message = "User doesn't exist")
        }
    }
}