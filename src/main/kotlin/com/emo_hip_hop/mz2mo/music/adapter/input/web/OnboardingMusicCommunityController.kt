package com.emo_hip_hop.mz2mo.music.adapter.input.web

import com.emo_hip_hop.mz2mo.global.WebAdapter
import com.emo_hip_hop.mz2mo.music.adapter.input.web.response.MusicCommunityResponse
import com.emo_hip_hop.mz2mo.music.application.port.input.QueryMusicCommunityUseCase
import com.emo_hip_hop.mz2mo.music.domain.MusicId
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@WebAdapter
@Tag(name = "Onboarding Music Communities", description = "온보딩 음악 커뮤니티 API")
@RestController
@RequestMapping("/api/v1/onboarding/music/community")
class OnboardingMusicCommunityController(
    private val queryMusicCommunityUseCase: QueryMusicCommunityUseCase,
    @Value("\${mz2mo.onboarding.music.id}")
    private val onboardingMusicId: String
) {
    @GetMapping
    @Operation(summary = "온보딩 음악 커뮤니티 조회", description = "온보딩 음악 커뮤니티를 조회합니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "음악 커뮤니티 조회 성공",
                content = [Content(schema = Schema(implementation = MusicCommunityResponse::class))]
            ),
            ApiResponse(
                responseCode = "400", description = "요청값이 올바르지 않을 경우",
                content = [Content(schema = Schema(implementation = String::class))]
            ),
            ApiResponse(
                responseCode = "404", description = "관련 자원을 찾을 수 없을경우",
                content = [Content(schema = Schema(implementation = String::class))]
            ),
            ApiResponse(
                responseCode = "500", description = "서버 내부 오류가 발생했을 경우",
                content = [Content(schema = Schema(implementation = String::class))]
            ),
        ]
    )
    fun queryMusicCommunityByMusicId(): ResponseEntity<MusicCommunityResponse> {
        val musicId = MusicId(onboardingMusicId)
        val domain = queryMusicCommunityUseCase(musicId)
        val response = domain.toResponse()
        return ResponseEntity.ok(response)
    }
}