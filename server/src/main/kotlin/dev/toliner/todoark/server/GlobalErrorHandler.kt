package dev.toliner.todoark.server

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error

@Controller("/errors")
class GlobalErrorHandler {
    @Error(exception = IllegalStateException::class)
    fun illegalArgument(exception: IllegalArgumentException): HttpResponse<ErrorResponse> {
        return HttpResponse.badRequest<ErrorResponse>().body(
            ErrorResponse(
                error = IllegalArgumentException::class.simpleName!!,
                message = exception.message!!,
            ),
        )
    }

    data class ErrorResponse(
        val error: String,
        val message: String,
    )
}
