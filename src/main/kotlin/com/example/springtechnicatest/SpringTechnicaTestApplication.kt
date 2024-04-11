package com.example.springtechnicatest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class SpringTechnicaTestApplication

fun main(args: Array<String>) {
	runApplication<SpringTechnicaTestApplication>(*args)

    @GetMapping("/")
    fun test(): String{
        return "Welcome"
    }
}
