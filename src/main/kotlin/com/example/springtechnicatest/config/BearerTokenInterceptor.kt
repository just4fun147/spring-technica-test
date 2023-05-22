package com.example.springtechnicatest.config

import com.example.springtechnicatest.repository.TokenRepository
import com.example.springtechnicatest.service.TokenServices
import com.example.springtechnicatest.util.JWTGenerator
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class BearerTokenInterceptor(
    val tokenServices: TokenServices
):HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val temp =
            (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?)!!.request.getHeader("Authorization")
        val token = temp.drop(7)

        if(!tokenServices.checkToken(token) || JWTGenerator().decodeJWT(token)){
            val result = mapOf<String, String>("status" to "F", "message" to "Invalid Token")
            response.writer.write(convertObjectToJson(result))
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            response.status = 401
            return false
        }
        return true

    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?,
    ) {
        super.postHandle(request, response, handler, modelAndView)
    }

    fun convertObjectToJson(dto : Map<String, String>): String {
        return ObjectMapper().writeValueAsString(dto)
    }
}
