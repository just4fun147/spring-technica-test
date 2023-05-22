package com.example.springtechnicatest.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.intellij.lang.annotations.RegExp
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class DescBrandDto (
    @field:Size(max=10, message = "DESC_BRAND max lenght is 10")
    @field:Pattern(regexp = "^[a-zA-Z0-9]*$", message = "DESC_BRAND must just alphanumeric")
    @field:JsonProperty("DESC_BRAND")
    val desc : String?,
)