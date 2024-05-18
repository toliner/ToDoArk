package dev.toliner.todoark.server.data.entity

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.time.Instant

@MappedEntity(value = "authorized_user")
data class AuthorizedUserEntity(
    @field:Id
    val id: Long,
    var userName: String,
    var password: String,
    var discordId: Long,
    @DateCreated
    var createdAt: Instant,
    @DateUpdated
    var updatedAt: Instant
)
