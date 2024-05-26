package dev.toliner.todoark.server.data.entity

import io.micronaut.data.annotation.DateCreated
import io.micronaut.data.annotation.DateUpdated
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import java.time.Instant

@MappedEntity("users")
data class UserEntity(
    @get:Id
    var id: Int,
    var openid: String,
    var nickname: String,
    @DateCreated
    var createdAt: Instant,
    @DateUpdated
    var updatedAt: Instant,
)

@MappedEntity("users")
data class UserEntityForCreate(
    @get:Id
    @GeneratedValue
    var id: Int? = null,
    var openid: String,
    var nickname: String,
    @DateCreated
    var createdAt: Instant? = null,
    @DateUpdated
    var updatedAt: Instant? = null,
)
