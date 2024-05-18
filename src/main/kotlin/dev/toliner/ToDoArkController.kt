package dev.toliner

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/toDoArk")
class ToDoArkController {
    @Get(uri = "/", produces = ["text/plain"])
    fun index(): String {
        return "Example Response"
    }
}
