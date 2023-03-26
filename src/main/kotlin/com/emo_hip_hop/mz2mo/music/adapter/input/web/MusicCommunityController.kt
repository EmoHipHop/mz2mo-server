package com.emo_hip_hop.mz2mo.music.adapter.input.web

import com.emo_hip_hop.mz2mo.global.WebAdapter
import com.emo_hip_hop.mz2mo.global.common.domain.Pageable
import com.emo_hip_hop.mz2mo.music.adapter.input.web.request.AddMusicCommunityRequest
import com.emo_hip_hop.mz2mo.music.adapter.input.web.response.MusicCommunitiesResponse
import com.emo_hip_hop.mz2mo.music.adapter.input.web.response.MusicCommunityResponse
import com.emo_hip_hop.mz2mo.music.application.port.input.*
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


@WebAdapter
@Tag(name = "Music Communities", description = "음악 커뮤니티 API")
@RestController
@RequestMapping("/api/v1/musics/communities")
class MusicCommunityController(
    private val addMusicCommunityUseCase: AddMusicCommunityUseCase,
    private val queryMusicCommunityUseCase: QueryMusicCommunityUseCase,
    private val searchMusicCommunityUseCase: SearchMusicCommunityUseCase
) {
    @PostMapping
    @Operation(summary = "음악 커뮤니티 추가", description = "음악 커뮤니티를 추가합니다.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "음악 커뮤니티 추가 성공",
            content = [Content(schema = Schema(implementation = MusicCommunityResponse::class))]),
        ApiResponse(responseCode = "400", description = "요청값이 올바르지 않을 경우",
            content = [Content(schema = Schema(implementation = String::class))])
    ])
    fun addMusicCommunity(@RequestBody request: AddMusicCommunityRequest): ResponseEntity<MusicCommunityResponse> {
        val command = AddMusicCommunityCommand(request.youtubeId)
        val domain = addMusicCommunityUseCase(command)
        val response = domain.toResponse()
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @GetMapping("/{musicId}")
    @Operation(summary = "음악 커뮤니티 조회", description = "음악 id를 통해 음악 커뮤니티를 조회합니다.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "음악 커뮤니티 조회 성공",
            content = [Content(schema = Schema(implementation = MusicCommunityResponse::class))]),
        ApiResponse(responseCode = "400", description = "요청값이 올바르지 않을 경우",
            content = [Content(schema = Schema(implementation = String::class))]),
        ApiResponse(responseCode = "500", description = "서버 내부 오류가 발생했을 경우",
            content = [Content(schema = Schema(implementation = String::class))]),
    ])
    fun queryMusicCommunityByMusicId(@PathVariable("musicId") rawMusicId: String): ResponseEntity<MusicCommunityResponse> {
        val musicId = MusicId(rawMusicId)
        val domain = queryMusicCommunityUseCase(musicId)
        val response = domain.toResponse()
        return ResponseEntity.ok(response)
    }

    @GetMapping("/search")
    @Operation(summary = "음악 커뮤니티 검색", description = "음악 커뮤니티를 검색합니다. 검색 결과 목록을 반환합니다.")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "음악 커뮤니티 검색 성공",
            content = [Content(schema = Schema(implementation = MusicCommunityResponse::class))]),
        ApiResponse(responseCode = "400", description = "요청값이 올바르지 않을 경우",
            content = [Content(schema = Schema(implementation = String::class))]),
        ApiResponse(responseCode = "500", description = "서버 내부 오류가 발생했을 경우",
            content = [Content(schema = Schema(implementation = String::class))]),
    ])
    fun searchMusicCommunity(
        @RequestParam lastIndexId: String,
        @RequestParam pageSize: Int
    ): ResponseEntity<MusicCommunitiesResponse> {
        val pageable = Pageable.of(lastIndexId, pageSize)
        val query = SearchMusicCommunityQuery(pageable)
        val domain = searchMusicCommunityUseCase(query)
        val response = domain.toResponse()
        return ResponseEntity.ok(response)
    }
}