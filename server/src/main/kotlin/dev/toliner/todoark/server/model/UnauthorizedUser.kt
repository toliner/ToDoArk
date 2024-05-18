package dev.toliner.todoark.server.model

import io.micronaut.serde.annotation.Serdeable

// @Introduction
@Serdeable
data class UnauthorizedUser(
    val userName: String,
    val authCode: String,
)
