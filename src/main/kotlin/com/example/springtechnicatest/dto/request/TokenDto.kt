package com.example.springtechnicatest.dto.request

import javax.validation.constraints.NotBlank

data class TokenDto (
    @field:NotBlank(message = "grant_type must not null")
    val grant_type : String? = "",
)