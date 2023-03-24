package com.emo_hip_hop.mz2mo.music.adapter.input.web

import com.emo_hip_hop.mz2mo.music.domain.MusicAlreadyExistsException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackageClasses = [MusicArticleController::class])
class MusicArticleControllerAdvice {
    @ExceptionHandler(MusicAlreadyExistsException::class)
    fun handle(e: MusicAlreadyExistsException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }
}