package com.emo_hip_hop.mz2mo.music.adapter.input.web

import com.emo_hip_hop.mz2mo.music.domain.MusicAlreadyExistsException
import com.emo_hip_hop.mz2mo.music.domain.MusicCommunityPartialNotFoundException
import com.emo_hip_hop.mz2mo.music.domain.MusicOutOfSyncException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackageClasses = [MusicCommunityController::class])
class MusicCommunityControllerAdvice {
    @ExceptionHandler(MusicAlreadyExistsException::class)
    fun handle(e: MusicAlreadyExistsException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }
    @ExceptionHandler(MusicOutOfSyncException::class)
    fun handle(e: MusicOutOfSyncException): ResponseEntity<String> {
        return ResponseEntity.internalServerError().body(e.message)
    }
    @ExceptionHandler(MusicCommunityPartialNotFoundException::class)
    fun handle(e: MusicCommunityPartialNotFoundException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }
}