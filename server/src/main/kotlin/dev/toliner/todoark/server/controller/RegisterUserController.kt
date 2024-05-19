package dev.toliner.todoark.server.controller

import dev.toliner.todoarc.model.endpoint.RegisterUnauthorized
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
    suspend fun registerUnauthorized(request: RegisterUnauthorized.Request) : RegisterUnauthorized.Response {
        val userName = request.userName
        if (!userName.matches(userNameRegex)) throw IllegalArgumentException("Invalid user name")
        val result = service.registerUnauthorizedUser(userName)
        return RegisterUnauthorized.Response(
            userName = result.userName,
            authCode = result.authCode
        )
    }
}
