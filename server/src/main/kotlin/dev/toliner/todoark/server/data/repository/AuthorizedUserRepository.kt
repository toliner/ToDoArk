package dev.toliner.todoark.server.data.repository

import dev.toliner.todoark.server.data.entity.AuthorizedUserEntity
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository

@JdbcRepository(dialect = Dialect.POSTGRES)
interface AuthorizedUserRepository: CoroutineCrudRepository<AuthorizedUserEntity, Long> {
    suspend fun findByUserName(username: String): AuthorizedUserEntity
}