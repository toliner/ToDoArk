package dev.toliner.todoark.server.data.repository

import dev.toliner.todoark.server.data.entity.UserEntity
import dev.toliner.todoark.server.data.entity.UserEntityForCreate
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository

@JdbcRepository(dialect = Dialect.POSTGRES)
interface UserRepository : CoroutineCrudRepository<UserEntity, Int> {
    suspend fun findByOpenid(openid: String): UserEntity?
}

@JdbcRepository(dialect = Dialect.POSTGRES)
interface UserRepositoryForCreate : CoroutineCrudRepository<UserEntityForCreate, Int>
