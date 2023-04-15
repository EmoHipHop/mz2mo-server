package com.emo_hip_hop.mz2mo.music.adapter.input.web

import com.emo_hip_hop.mz2mo.account.application.port.input.QueryLoginedAccountUseCase
import com.emo_hip_hop.mz2mo.emoji.application.port.input.QueryEmojiUseCase
import com.emo_hip_hop.mz2mo.global.WebAdapter
import com.emo_hip_hop.mz2mo.global.validate.domain.UUID_PATTERN
import com.emo_hip_hop.mz2mo.music.adapter.input.web.request.AddMusicVoteRequest
import com.emo_hip_hop.mz2mo.music.adapter.input.web.request.RemoveMusicVoteRequest
import com.emo_hip_hop.mz2mo.music.adapter.input.web.response.MusicCommunityResponse
import com.emo_hip_hop.mz2mo.music.application.port.input.AddMusicVoteCommand
import com.emo_hip_hop.mz2mo.music.application.port.input.AddMusicVoteUseCase
import com.emo_hip_hop.mz2mo.music.application.port.input.RemoveMusicVoteCommand
import com.emo_hip_hop.mz2mo.music.application.port.input.RemoveMusicVoteUseCase
import com.emo_hip_hop.mz2mo.music.domain.MusicId
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Pattern

@WebAdapter
@Tag(name = "Music Votes", description = "음악 투표 API")
@RestController
@RequestMapping("/api/v1/musics/{musicId}/votes")
class MusicVoteController(
    private val addMusicVoteUseCase: AddMusicVoteUseCase,
    private val removeMusicVoteUseCase: RemoveMusicVoteUseCase,
    private val queryLoginedAccountUseCase: QueryLoginedAccountUseCase,
    private val queryEmojiUseCase: QueryEmojiUseCase,
) {
    @PostMapping
    @Operation(summary = "음악 투표 추가", description = "음악 투표를 추가합니다.")
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
        @PathVariable("musicId")
        @Pattern(regexp = UUID_PATTERN, message = "음악 ID 형식이 올바르지 않습니다.")
        rawMusicId: String,
        @RequestBody request: AddMusicVoteRequest
    ): ResponseEntity<MusicCommunityResponse> {
        val musicId = MusicId(rawMusicId)
        val accountId = queryLoginedAccountUseCase().id
        val emojiId = queryEmojiUseCase(request.rawEmoji).id
        val command = AddMusicVoteCommand(musicId, accountId, emojiId)
        val domain = addMusicVoteUseCase(command)
        val response = domain.toResponse()
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @DeleteMapping
    @Operation(summary = "음악 투표 삭제", description = "음악 투표를 삭제합니다.")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "음악 투표 삭제 성공",
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
    fun removeMusicVote(
        @PathVariable("musicId")
        @Pattern(regexp = UUID_PATTERN, message = "음악 ID 형식이 올바르지 않습니다.")
        rawMusicId: String,
        @RequestBody request: RemoveMusicVoteRequest
    ): ResponseEntity<MusicCommunityResponse> {
        val musicId = MusicId(rawMusicId)
        val accountId = queryLoginedAccountUseCase().id
        val emojiId = queryEmojiUseCase(request.rawEmoji).id
        val command = RemoveMusicVoteCommand(musicId, accountId, emojiId)
        val domain = removeMusicVoteUseCase(command)
        val response = domain.toResponse()
        return ResponseEntity.ok(response)
    }
}