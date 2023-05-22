package com.example.springtechnicatest.repository

import com.example.springtechnicatest.entity.Brand
import org.springframework.data.jpa.repository.JpaRepository

interface BrandRepository:JpaRepository<Brand, Long> {

}