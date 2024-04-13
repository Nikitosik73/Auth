package ru.paramonov.features.registration

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ru.paramonov.features.cache.InMemoryCache
import ru.paramonov.features.cache.TokensCache
import ru.paramonov.utils.isValidEmail
import java.util.UUID

fun Application.configureRegistrationRouting() {
    routing {
        post("/registration") {
            val receive = call.receive<RegistrationReceiveRemote>()

            if (!receive.email.isValidEmail()) {
                call.respond(HttpStatusCode.BadRequest, message = "Email is not valid")
            }

            if (InMemoryCache.users.map { it.username }.contains(receive.username)) {
                call.respond(HttpStatusCode.Conflict, message = "Username already in use")
            }

            val token = UUID.randomUUID().toString()
            InMemoryCache.users.add(receive)
            InMemoryCache.tokens.add(TokensCache(username = receive.username, token = token))

            call.respond(RegistrationReceiveResponse(token = token))
        }
    }
}