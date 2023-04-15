package com.emo_hip_hop.mz2mo.music.adapter.input.web

import com.emo_hip_hop.mz2mo.global.WebAdapter
import com.emo_hip_hop.mz2mo.music.adapter.input.web.request.AddMusicVoteRequest
import com.emo_hip_hop.mz2mo.music.adapter.input.web.response.MusicCommunityResponse
import com.emo_hip_hop.mz2mo.music.adapter.input.web.response.MusicVotePercentagesResponse
import com.emo_hip_hop.mz2mo.music.application.port.input.QueryMusicCommunityUseCase
import com.emo_hip_hop.mz2mo.music.application.port.input.QueryMusicVotePercentageByEmojiUseCase
import com.emo_hip_hop.mz2mo.music.domain.MusicId
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@WebAdapter
@Tag(name = "Onboarding Music Votes", description = "온보딩 음악 투표 API")
@RestController
@RequestMapping("/api/v1/onboarding/music/votes")
class OnboardingMusicVoteController(
    private val queryMusicVotePercentageUseCase: QueryMusicVotePercentageByEmojiUseCase,
    private val queryMusicCommunityUseCase: QueryMusicCommunityUseCase,
    @Value("\${mz2mo.onboarding.music.id}")
    private val onboardingMusicId: String
) {
    @GetMapping
    @Operation(summary = "온보딩 음악 투표율 조회", description = "온보딩 음악 투표율을 조회합니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "음악 투표율 조회 성공",
                content = [Content(schema = Schema(implementation = MusicCommunityResponse::class))]
            ),
            ApiResponse(
                responseCode = "404", description = "관련 자원을 찾을 수 없을경우",
                content = [Content(schema = Schema(implementation = String::class))]
            )
        ]
    )
    fun queryMusicVotePercentage(): ResponseEntity<MusicVotePercentagesResponse> {
        val musicId = MusicId(onboardingMusicId)
        val domain = queryMusicVotePercentageUseCase(musicId)
        val response = domain.toResponse()
        return ResponseEntity.ok(response)
    }

    @PostMapping
    @Operation(summary = "온보딩 음악 투표 추가", description = "온보딩 음악 투표를 추가합니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201", description = "음악 투표 추가 성공",
                content = [Content(schema = Schema(implementation = MusicCommunityResponse::class))]
            ),
            ApiResponse(
                responseCode = "400", description = "요청값이 올바르지 않을 경우",
                content = [Content(schema = Schema(implementation = String::class))]
            ),
            ApiResponse(
                responseCode = "404", description = "관련 자원을 찾을 수 없을경우",
                content = [Content(schema = Schema(implementation = String::class))]
            )
        ]
    )
    fun addMusicVote(
        @RequestBody request: AddMusicVoteRequest
    ): ResponseEntity<MusicCommunityResponse> {
        val musicId = MusicId(onboardingMusicId)
        // guest의 투표는 저장하지 않는다.
        // val accountId = queryLoginedAccountUseCase().id
        // val emojiId = queryEmojiUseCase(request.rawEmoji).id
        // val command = AddMusicVoteCommand(musicId, accountId, emojiId)
        // addMusicVoteUseCase(command)
        val domain = queryMusicCommunityUseCase(musicId)
        val response = domain.toResponse()
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}