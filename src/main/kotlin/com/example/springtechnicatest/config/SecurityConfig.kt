package com.example.springtechnicatest.config

import com.example.springtechnicatest.security.CustomAuthenticationProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder


@Configuration
@EnableWebSecurity
class SecurityConfig(
//    val customAuthenticationProvider: CustomAuthenticationProvider
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun encoder():PasswordEncoder{
        return BCryptPasswordEncoder()
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "APISERVICE/OAUTH/TOKEN").permitAll()
            .and()
            .csrf().disable()
    }
    override fun configure(auth: AuthenticationManagerBuilder) {

        auth.inMemoryAuthentication()
            .withUser("Pandu")
            .password(encoder().encode("Password1"))
            .roles("ADMIN")
//        auth.authenticationProvider(customAuthenticationProvider)
    }



    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

}