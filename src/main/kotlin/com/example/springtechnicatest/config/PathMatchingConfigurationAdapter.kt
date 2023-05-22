package com.example.springtechnicatest.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class PathMatchingConfigurationAdapter(
    val apiKeyInterceptor: ApiKeyInterceptor,
    val basicAuthInterceptor: BasicAuthInterceptor,
    val bearerTokenInterceptor: BearerTokenInterceptor,
    val headerApi2Interceptor: HeaderApi2Interceptor
): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(apiKeyInterceptor)
        registry.addInterceptor(basicAuthInterceptor).addPathPatterns("/APISERVICE/OAUTH/TOKEN")
        registry.addInterceptor(headerApi2Interceptor).addPathPatterns("/APISERVICE/UNIT/GETBRAND")
        registry.addInterceptor(bearerTokenInterceptor).addPathPatterns("/APISERVICE/UNIT/GETBRAND")
    }
}
