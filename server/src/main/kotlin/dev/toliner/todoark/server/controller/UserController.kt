package dev.toliner.todoark.server.controller

import dev.toliner.todoarc.model.endpoint.GetUser
import dev.toliner.todoark.server.service.UserService
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.utils.SecurityService
import io.micronaut.session.Session
import kotlin.jvm.optionals.getOrNull

@Controller("/user")
class UserController(
    private val securityService: SecurityService,
    private val userService: UserService,
) {
    @Get("/")
    suspend fun getProfile(session: Session): GetUser.Response {
        val userId = session["uid"].getOrNull()
        val user = if (userId != null) {
            userService.getUserById(userId as Int)
        } else {
            val attributes = securityService.authentication.get().attributes
            val openid = attributes["name"] as String
            val nickname = attributes["nickname"] as String
            userService.getOrInitializeUser(openid, nickname).also { user ->
                session.put("uid", user.id)
            }
        }
        return GetUser.Response(user.id, user.nickname)
    }
}
