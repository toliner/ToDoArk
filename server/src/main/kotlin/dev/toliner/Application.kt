package dev.toliner

import io.micronaut.context.ApplicationContextBuilder
import io.micronaut.context.ApplicationContextConfigurer
import io.micronaut.context.annotation.ContextConfigurer
import io.micronaut.runtime.Micronaut.run
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.info.*

@OpenAPIDefinition(
    info = Info(
        title = "ToDoArk",
        version = "0.0",
    ),
)
object Api

@ContextConfigurer
class Configurer : ApplicationContextConfigurer {
    override fun configure(builder: ApplicationContextBuilder) {
        builder.defaultEnvironments("dev")
    }
}

fun main(args: Array<String>) {
    run(*args)
}
