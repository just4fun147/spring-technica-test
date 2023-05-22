package com.example.springtechnicatest.controller

import com.example.springtechnicatest.dto.request.BrandDto
import com.example.springtechnicatest.dto.request.DescBrandDto
import com.example.springtechnicatest.dto.request.TokenDto
import com.example.springtechnicatest.dto.response.BaseResponseDto
import com.example.springtechnicatest.entity.User
import com.example.springtechnicatest.repository.UserRepository
import com.example.springtechnicatest.service.TokenServices
import com.example.springtechnicatest.exception.CustomExceptionHandler
import com.example.springtechnicatest.repository.TokenRepository
import com.example.springtechnicatest.service.BrandService
import com.example.springtechnicatest.util.JWTGenerator
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("APISERVICE")
@Validated
class Controller(
    val repo: UserRepository,
    val tokenService: TokenServices,
    val brandService: BrandService,
    val tokenRepository: TokenRepository
) {

    @PostMapping("/OAUTH/TOKEN")
    fun token(@Valid @RequestBody request: TokenDto): ResponseEntity<BaseResponseDto<Any>> {
        if (request.grant_type == "client_credentials1" || request.grant_type == "client_credentials2" || request.grant_type == "client_credentials3") {
            val temp =
                (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?)!!.request.getHeader("Authorization")
            val trim = temp.drop(6)
            val basics = Base64.getDecoder().decode(trim)
            val basic = String(basics)
            val username = basic.substringBefore(":")
            val usr = repo.findByEmailPassword(username)
            val id = usr!!.id
            val token = tokenService.tokenSave(id, username)
            return ResponseEntity.ok(
                BaseResponseDto(
                    status = "T",
                    message = "Success",
                    data = token
                )
            )
        } else {
            return ResponseEntity.ok(
                BaseResponseDto(
                    status = "F",
                    message = "Invalid Credentials",
                    data = ""
                )
            )
        }
    }

        @PostMapping("/UNIT/GETBRAND")
        fun brand(@Valid @RequestBody request:BrandDto):ResponseEntity<BaseResponseDto<Any>> {

            if(request.getListFilterUnitBrand!!.desc === ""){
                val data = brandService.brandGetAll()
                return ResponseEntity.ok(
                    BaseResponseDto(
                        status = "T",
                        message = "Get All Brand Success",
                        data = data
                    )
                )
            }else{
                val data = brandService.brandById(request.getListFilterUnitBrand!!.desc!!.toLong())
                return ResponseEntity.ok(
                    BaseResponseDto(
                        status = "T",
                        message = "Get Brand Success",
                        data = data
                    )
                )
            }
        }

//        val token = JWTGenerator().createJWT(request.grant_type)


//    @GetMapping("/OAUTH/TOKEN")
//    fun test(): ResponseEntity<BaseResponseDto<Any>> {
//        val user = repo.findByEmailPassword("Pandu")
//        return ResponseEntity.ok(
//            BaseResponseDto(
//                status = "T",
//                message = "Login Success",
//                data = user!!
//            )
//        )
//    }
    @PostMapping("/TOKEN")
    fun test(@RequestBody request: TokenDto): ResponseEntity<BaseResponseDto<Any>> {
        val user = tokenService.checkToken(request.grant_type!!)
//        val user = tokenRepository.getAll()
        return ResponseEntity.ok(
            BaseResponseDto(
                status = "T",
                message = "Login Success",
                data = user
            )
        )
    }
}