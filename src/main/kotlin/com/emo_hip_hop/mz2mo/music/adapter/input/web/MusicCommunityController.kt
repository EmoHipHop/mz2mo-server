package com.emo_hip_hop.mz2mo.music.adapter.input.web

import com.emo_hip_hop.mz2mo.global.WebAdapter
import com.emo_hip_hop.mz2mo.music.application.port.input.AddMusicCommunityCommand
import com.emo_hip_hop.mz2mo.music.application.port.input.AddMusicCommunityUseCase
import com.emo_hip_hop.mz2mo.music.application.port.input.QueryMusicCommunityUseCase
import com.emo_hip_hop.mz2mo.music.domain.MusicId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@WebAdapter
@RestController
@RequestMapping("/api/v1/musics/communities")
class MusicCommunityController(
    private val addMusicCommunityUseCase: AddMusicCommunityUseCase,
    private val queryMusicCommunityUseCase: QueryMusicCommunityUseCase
) {
    @PostMapping
    fun addMusicCommunity(@RequestBody request: AddMusicCommunityRequest): ResponseEntity<MusicCommunityResponse> {
        val command = AddMusicCommunityCommand(request.youtubeId)
        val domain = addMusicCommunityUseCase(command)
        val response = domain.toResponse()
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @GetMapping("/{musicId}")
    fun queryMusicCommunityByMusicId(@PathVariable("musicId") rawMusicId: String): ResponseEntity<MusicCommunityResponse> {
        val musicId = MusicId(rawMusicId)
        val domain = queryMusicCommunityUseCase(musicId)
        val response = domain.toResponse()
        return ResponseEntity.ok(response)
    }
}