package com.example.springtechnicatest.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class BrandDto (
    @field:Valid
    val getListFilterUnitBrand: DescBrandDto?
){
    constructor() : this(null)
}
