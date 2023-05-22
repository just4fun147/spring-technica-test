package com.example.springtechnicatest.service.impl

import com.example.springtechnicatest.entity.Token
import com.example.springtechnicatest.entity.User
import com.example.springtechnicatest.repository.TokenRepository
import com.example.springtechnicatest.repository.UserRepository
import com.example.springtechnicatest.service.TokenServices
import com.example.springtechnicatest.util.JWTGenerator
import org.springframework.stereotype.Service

@Service
class TokenServiceImpl(
    val tokenRepository: TokenRepository,
    val userRepository: UserRepository
): TokenServices {

    override fun tokenSave(userId: Long?,username: String):String {
        if(tokenRepository.existsById(userId!!)){
            val token = tokenRepository.findById(userId).get()
            if(JWTGenerator().decodeJWT(token.token!!)){
                val temp = JWTGenerator().createJWT(userId,username)
                val tempToken = token.copy(token = temp)
                tokenRepository.save(tempToken)
                return temp
            }
            return token.token
        }else{
            val tempUser = userRepository.findByEmailPassword(username)
            val temp = JWTGenerator().createJWT(userId,username)
            val token = Token(user=tempUser!!,token=temp)
            tokenRepository.save(token)
            return temp
        }

}

    override fun tokenById(id: Long): String {
        val token = tokenRepository.findById(id)
        return token.get().token!!
    }

    override fun checkToken(token:String): Boolean {
        var temps = 0
        val temp = tokenRepository.getAll()
        for(example in temp){
            if(example.equals(token)){
                temps = 1
            }
        }
        if(temps==0){
            return false
        }else{
            return true
        }
    }

//    override fun tokens(token: String): Boolean {
//        val temp = tokenRepository.findToken(token)
//        if(temp!=null){
//            return true
//        }else{
//            return false
//        }
//    }

    override fun tokenCreate(userId: Long?, user: User):String{
        val temp = JWTGenerator().createJWT(userId!!,user.username!!)
//        val tempUser = userRepository.findByEmailPassword(user)
        val token = Token(user=user, token = temp)
        return "r"
    }

}