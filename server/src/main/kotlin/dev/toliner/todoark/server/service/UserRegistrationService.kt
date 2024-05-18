package dev.toliner.todoark.server.service

import dev.toliner.todoark.server.data.entity.UnauthorizedUserEntity
import dev.toliner.todoark.server.data.repository.AuthorizedUserRepository
import dev.toliner.todoark.server.data.repository.UnauthorizedUserRepository
import dev.toliner.todoark.server.model.UnauthorizedUser
import jakarta.inject.Singleton
import kotlin.random.Random

@Singleton
class UserRegistrationService(
    private val unauthorizedUserRepository: UnauthorizedUserRepository,
    private val authorizedUserRepository: AuthorizedUserRepository
) {
    private val authCodeArray = arrayOf(
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
        'u', 'v', 'w', 'x', 'y', 'z'
    )

    suspend fun registerUnauthorizedUser(userName: String): UnauthorizedUser {
        val random = Random(System.currentTimeMillis())
        val authCode = Array(16) { authCodeArray[random.nextInt(0, 36)] }.joinToString(separator = "")
        val entity = unauthorizedUserRepository.save(UnauthorizedUserEntity(
            userName = userName,
            authCode = authCode,
        ))
        return UnauthorizedUser(
            userName = entity.userName,
            authCode = entity.authCode
        )
    }
}