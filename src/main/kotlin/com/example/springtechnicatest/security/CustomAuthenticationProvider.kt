package com.example.springtechnicatest.security

import com.example.springtechnicatest.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationProvider(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder = BCryptPasswordEncoder()
): AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val username = authentication.name
        val password = authentication.credentials.toString()
        println("auth")
        val user = userRepository.findByEmailPassword(username)?:throw UsernameNotFoundException("Invalid username")


        if(passwordEncoder.matches(password,user?.password)){
            return UsernamePasswordAuthenticationToken(username,password)
        }else{
            throw BadCredentialsException("Invalid Credentials")
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return authentication!!.equals(UsernamePasswordAuthenticationToken::class)
    }
}