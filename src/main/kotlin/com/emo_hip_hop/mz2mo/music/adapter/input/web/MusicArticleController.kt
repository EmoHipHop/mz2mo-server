package com.emo_hip_hop.mz2mo.music.adapter.input.web

import com.emo_hip_hop.mz2mo.global.WebAdapter
import com.emo_hip_hop.mz2mo.music.application.port.input.AddMusicArticleCommand
import com.emo_hip_hop.mz2mo.music.application.port.input.AddMusicArticleUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@WebAdapter
@RestController
@RequestMapping("/api/v1/musics/articles")
class MusicArticleController(
    private val addMusicArticleUseCase: AddMusicArticleUseCase
) {
    @PostMapping
    fun addMusicArticle(@RequestBody request: AddMusicArticleRequest): ResponseEntity<MusicArticleResponse> {
        val command = AddMusicArticleCommand(request.youtubeId)
        val domain = addMusicArticleUseCase(command)
        val response = domain.toResponse()
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}