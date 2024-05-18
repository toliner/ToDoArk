package dev.toliner.todoark.server.data.repository

import dev.toliner.todoark.server.data.entity.UnauthorizedUserEntity
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository

@JdbcRepository(dialect = Dialect.POSTGRES)
interface UnauthorizedUserRepository : CoroutineCrudRepository<UnauthorizedUserEntity, String> {
    suspend fun findByAuthCode(authCode: String): UnauthorizedUserEntity
}