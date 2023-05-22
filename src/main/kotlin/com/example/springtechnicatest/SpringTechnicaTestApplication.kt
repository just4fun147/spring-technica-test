package com.example.springtechnicatest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringTechnicaTestApplication

fun main(args: Array<String>) {
	runApplication<SpringTechnicaTestApplication>(*args)
}
