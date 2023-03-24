package com.emo_hip_hop.mz2mo.music.adapter.input.web

import com.emo_hip_hop.mz2mo.global.WebAdapter
import com.emo_hip_hop.mz2mo.music.application.port.input.AddMusicArticleCommand
import com.emo_hip_hop.mz2mo.music.application.port.input.AddMusicArticleUseCase
import com.emo_hip_hop.mz2mo.music.application.port.input.QueryMusicArticleUseCase
import com.emo_hip_hop.mz2mo.music.domain.MusicId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@WebAdapter
@RestController
@RequestMapping("/api/v1/musics/articles")
class MusicArticleController(
    private val addMusicArticleUseCase: AddMusicArticleUseCase,
    private val queryMusicArticleUseCase: QueryMusicArticleUseCase
) {
    @PostMapping
    fun addMusicArticle(@RequestBody request: AddMusicArticleRequest): ResponseEntity<MusicArticleResponse> {
        val command = AddMusicArticleCommand(request.youtubeId)
        val domain = addMusicArticleUseCase(command)
        val response = domain.toResponse()
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @GetMapping("/{musicId}")
    fun queryMusicArticleByMusicId(@PathVariable("musicId") rawMusicId: String): ResponseEntity<MusicArticleResponse> {
        val musicId = MusicId(rawMusicId)
        val domain = queryMusicArticleUseCase(musicId)
        val response = domain.toResponse()
        return ResponseEntity.ok(response)
    }
}