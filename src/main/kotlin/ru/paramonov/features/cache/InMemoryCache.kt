package ru.paramonov.features.cache

import ru.paramonov.features.registration.RegistrationReceiveRemote

data class TokensCache(
    val username: String,
    val token: String
)

object InMemoryCache {

    val users: MutableList<RegistrationReceiveRemote> = mutableListOf()
    val tokens: MutableList<TokensCache> = mutableListOf()
}