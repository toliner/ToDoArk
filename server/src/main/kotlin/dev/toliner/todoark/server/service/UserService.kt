package dev.toliner.todoark.server.service

import dev.toliner.todoark.server.data.entity.UserEntityForCreate
import dev.toliner.todoark.server.data.repository.UserRepository
import dev.toliner.todoark.server.data.repository.UserRepositoryForCreate
import dev.toliner.todoark.server.model.User
import dev.toliner.todoark.server.model.toUser
import jakarta.inject.Singleton

@Singleton
class UserService(
    private val userRepository: UserRepository,
    private val userCreateRepository: UserRepositoryForCreate,
) {
    suspend fun getOrInitializeUser(openId: String, nickname: String): User {
        val userEntity = userRepository.findByOpenid(openId)
        if (userEntity != null) return userEntity.toUser()
        val createdEntity = userCreateRepository.save(
            UserEntityForCreate(
                openid = openId,
                nickname = nickname,
            ),
        )
        return userRepository.findById(createdEntity.id!!)!!.toUser()
    }

    suspend fun getUserById(id: Int): User {
        val entity = userRepository.findById(id) ?: throw IllegalArgumentException("User with id $id not found")
        return entity.toUser()
    }
}
