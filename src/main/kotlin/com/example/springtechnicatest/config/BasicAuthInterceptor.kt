package com.example.springtechnicatest.config

import com.example.springtechnicatest.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.Base64
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class BasicAuthInterceptor(
    val userService: UserService
):HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val temp =
            (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?)!!.request.getHeader("Authorization")
        val trim = temp.drop(6)
        val basics = Base64.getDecoder().decode(trim)
        val basic = String(basics)
        val username = basic.substringBefore(":")
        val password = basic.substringAfter(":")
        val result = userService.login(username,password)
        if (!result){
            val result = mapOf<String, String>("status" to "F", "message" to "Invalid Auth")
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
