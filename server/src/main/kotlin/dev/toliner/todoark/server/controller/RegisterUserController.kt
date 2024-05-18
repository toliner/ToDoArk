package dev.toliner.todoark.server.controller

import dev.toliner.todoark.server.model.UnauthorizedUser
import dev.toliner.todoark.server.service.UserRegistrationService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/user/register", produces = [MediaType.APPLICATION_JSON], consumes = [MediaType.APPLICATION_JSON])
class RegisterUserController(
    private val service: UserRegistrationService
) {
    private val userNameRegex = Regex("^[a-zA-Z0-9_]{4,16}$")

    @Post(uri = "/")
    suspend fun registerUnauthorized(userName: String) : UnauthorizedUser {
        if (!userName.matches(userNameRegex)) throw IllegalArgumentException("Invalid user name")
        return service.registerUnauthorizedUser(userName)
    }

    data class RegisterUnauthorizedRequest(
        val userName: String
    )
}
