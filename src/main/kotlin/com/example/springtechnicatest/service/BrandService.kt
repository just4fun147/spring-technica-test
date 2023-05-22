package com.example.springtechnicatest.service

import com.example.springtechnicatest.dto.response.BrandDtoResponse

interface BrandService {
    fun brandGetAll(): List<BrandDtoResponse>
    fun brandById(id:Long): BrandDtoResponse
}