package com.example.springtechnicatest.service.impl

import com.example.springtechnicatest.repository.UserRepository
import com.example.springtechnicatest.service.UserService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    val userRepository: UserRepository
):UserService {
    override fun login(username: String, password: String): Boolean {
        val users = userRepository.findAll()
        for(user in users){
            if(user.username == username){
                if(BCryptPasswordEncoder().matches(password,user.password)){
                    return true
                }
                return false
            }
        }
        return false
    }
}