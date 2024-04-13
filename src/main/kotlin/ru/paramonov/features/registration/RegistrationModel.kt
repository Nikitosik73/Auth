package ru.paramonov.features.registration

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationReceiveRemote(
    val username: String,
    val email: String,
    val password: String
)

@Serializable
data class RegistrationReceiveResponse(
    val token: String
)
