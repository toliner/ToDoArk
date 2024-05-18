package dev.toliner.todoark.server.model

import io.micronaut.core.annotation.Introspected

@Introspected
data class AuthorizedUser(
    val id: Long,
    val userName: String,
    val password: String,
    val discordId: Long,
)
