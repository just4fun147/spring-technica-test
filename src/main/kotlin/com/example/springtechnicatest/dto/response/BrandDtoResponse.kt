package com.example.springtechnicatest.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class BrandDtoResponse (
    @field:JsonProperty("CD_BRAND")
    val cd: Long? = null,

    @field:JsonProperty("DESC_BRAND")
    val brand: String? = null,
)