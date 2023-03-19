package com.emo_hip_hop.mz2mo.global.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        http.cors().disable()
            .csrf().disable()
            .authorizeHttpRequests()
            .anyRequest().permitAll() //TODO User 관련 로직 작성 후 수정예정입니다. | Raul | 2023-03-19
            .and()
            .build()

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}