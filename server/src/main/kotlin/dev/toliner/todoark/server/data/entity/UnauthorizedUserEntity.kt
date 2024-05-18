package dev.toliner.todoark.server.data.entity

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.time.Instant

@MappedEntity(value = "unauthorized_user")
data class UnauthorizedUserEntity(
    @field:Id
    var userName: String,
    var authCode: String,
    @DateCreated
    var createdAt: Instant? = null,
    @DateUpdated
    var updatedAt: Instant? = null
)
