package com.example.springtechnicatest.service

import com.example.springtechnicatest.entity.Token
import com.example.springtechnicatest.entity.User

interface TokenServices {

    fun tokenSave(userId: Long?, username: String):String
    fun tokenCreate(userId: Long?, user: User):String
    fun tokenById(id:Long): String
    fun checkToken(token:String):Boolean
}