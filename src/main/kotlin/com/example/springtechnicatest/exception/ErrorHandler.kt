package com.example.springtechnicatest.exception

import com.example.springtechnicatest.dto.response.BaseResponseDto
import com.example.springtechnicatest.repository.TokenRepository
import com.example.springtechnicatest.util.JWTGenerator
import io.jsonwebtoken.ExpiredJwtException
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpClientErrorException.BadRequest
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ErrorHandler(
    val tokenRepository: TokenRepository
) {
//method
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleArgumentNotValidException(exception:MethodArgumentNotValidException):ResponseEntity<Any>{
        val errors = mutableListOf<String>()
        exception.bindingResult.fieldErrors.forEach{
            errors.add(it.defaultMessage!!)
        }
        val result = BaseResponseDto(
            "F",
            "Something Went Wrong",
            errors
        )
        return ResponseEntity.badRequest().body(result)
    }
//nullobject
    @ExceptionHandler(NullPointerException::class)
    fun handleArgumentNotNull(exception:NullPointerException):ResponseEntity<Any>{
        val result = BaseResponseDto(
            "F",
            "Something Went Wrong",
            ""
        )
        return ResponseEntity.badRequest().body(result)
    }
//badrequest
    @ExceptionHandler(BadRequest::class)
    fun handleArgumentBadRequest(exception:BadRequest):ResponseEntity<Any>{
        val result = BaseResponseDto(
            "F",
            "Something Went Wrong",
            ""
        )
        return ResponseEntity.badRequest().body(result)
    }
//custom
    @ExceptionHandler(CustomExceptionHandler::class)
    fun handleCustomException(exception: SqlExceptionHelper):ResponseEntity<Any>{
        val result = mapOf<String, Any>("status" to "F", "error" to "field", "message" to exception)
        return ResponseEntity.badRequest().body(result)

    }
//expired token
    @ExceptionHandler(value = [ExpiredJwtException::class])
    fun handleExpiredJwtException(ex: ExpiredJwtException, request: WebRequest): ResponseEntity<Any?>? {
        val requestUri = (request as ServletWebRequest).request.requestURI.toString()
        val token = tokenRepository.findById(1).get()
        val temp = JWTGenerator().createJWT(1,"Pandu")
        val tempToken = token.copy(token = temp)
        tokenRepository.save(tempToken)
        val result = BaseResponseDto(
            "T",
            "New Token",
            temp
        )
        return ResponseEntity.ok(result)
    }
}