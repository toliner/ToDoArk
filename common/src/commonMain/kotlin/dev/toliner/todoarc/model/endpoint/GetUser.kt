package dev.toliner.todoarc.model.endpoint

class GetUser {
    data class Response(
        val id: Int,
        val nickname: String
    )
}
