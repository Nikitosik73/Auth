package ru.paramonov

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import ru.paramonov.features.login.configureLoginRouting
import ru.paramonov.features.registration.configureRegistrationRouting
import ru.paramonov.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureLoginRouting()
    configureRegistrationRouting()
    configureSerialization()
}
