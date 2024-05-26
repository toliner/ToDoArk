package dev.toliner.todoark.server.model

import dev.toliner.todoark.server.data.entity.UserEntity

data class User(
    val id: Int,
    val openId: String,
    val nickname: String,
)

fun UserEntity.toUser() = User(
    id = id,
    openId = openid,
    nickname = nickname,
)
