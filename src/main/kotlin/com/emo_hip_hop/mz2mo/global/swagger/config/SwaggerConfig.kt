
package com.emo_hip_hop.mz2mo.global.swagger.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun groupedApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("V1 API")
            .pathsToMatch("/api/v1/**")
            .build()
    }

    @Bean
    fun v1Api(): OpenAPI {
        return Info().title("이호힙합 - mz2mo API V1")
            .description("이모힙합팀의 mz2mo 서비스 API 입니다!")
            .version("1.0.0")
            .let { info -> OpenAPI().info(info) }
    }
}