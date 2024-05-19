package dev.toliner.todoarc.model.endpoint

class RegisterUnauthorized {
    data class Request(
        val userName: String
    )
    data class Response(
        val userName: String,
        val authCode: String,
    )
}
