package com.example.springtechnicatest.service.impl

import com.example.springtechnicatest.dto.response.BrandDtoResponse
import com.example.springtechnicatest.repository.BrandRepository
import com.example.springtechnicatest.service.BrandService
import org.springframework.stereotype.Service

@Service
class BrandServiceImpl(
    val brandRepository: BrandRepository
):BrandService {

    override fun brandGetAll(): List<BrandDtoResponse> {
        val exampleEntities = brandRepository.findAll()
        val result = mutableListOf<BrandDtoResponse>()
        for(example in exampleEntities){
            result.add(
                BrandDtoResponse(
                    cd = example.id,
                    brand = example.desc,)
            )
        }
        return result
    }

    override fun brandById(id: Long): BrandDtoResponse {
        val exampleEntities = brandRepository.findById(id)
        return BrandDtoResponse(
                    cd = exampleEntities.get().id,
                    brand = exampleEntities.get().desc,)
    }

}