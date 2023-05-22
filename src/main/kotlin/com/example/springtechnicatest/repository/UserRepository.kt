package com.example.springtechnicatest.repository

import com.example.springtechnicatest.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository:JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM tbl_user WHERE username = ?", nativeQuery = true)
    fun findByEmailPassword(username: String?): User?

//    @Query(value = "SELECT * FROM tbl_user WHERE id = ?", nativeQuery = true)
//    fun findById(id: Long?): User?
}