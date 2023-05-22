package com.example.springtechnicatest.repository

import com.example.springtechnicatest.entity.Token
import com.example.springtechnicatest.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TokenRepository:JpaRepository<Token, Long> {
    @Query(value = "INSERT INTO tbl_token(user_id,token) VALUES(?,?) ", nativeQuery = true)
    fun add(userId:Long?,username: String?)
    @Query(value = "SELECT * FROM tbl_token WHERE user_id = ?", nativeQuery = true)
    fun findByUserId(userId: Long?): Token
    @Query(value = "SELECT * FROM tbl_token WHERE token = ? ", nativeQuery = true)
    fun findToken(token: String?): Token
    @Query(value = "SELECT token FROM tbl_token", nativeQuery = true)
    fun getAll(): List<String>
}