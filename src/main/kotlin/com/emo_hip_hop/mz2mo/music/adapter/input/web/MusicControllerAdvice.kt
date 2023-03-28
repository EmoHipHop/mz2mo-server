package com.emo_hip_hop.mz2mo.music.adapter.input.web

import com.emo_hip_hop.mz2mo.music.domain.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackageClasses = [MusicCommunityController::class, MusicVoteController::class])
class MusicControllerAdvice {
    @ExceptionHandler(MusicAlreadyExistsException::class)
    fun handle(e: MusicAlreadyExistsException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }
    @ExceptionHandler(MusicOutOfSyncException::class)
    fun handle(e: MusicOutOfSyncException): ResponseEntity<String> {
        return ResponseEntity.internalServerError().body(e.message)
    }
    @ExceptionHandler(MusicCommunityOrPartialNotFoundException::class)
    fun handle(e: MusicCommunityOrPartialNotFoundException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }
    @ExceptionHandler(AlreadyVoteException::class)
    fun handle(e: AlreadyVoteException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }
    @ExceptionHandler(EmptyMusicIdException::class)
    fun handle(e: EmptyMusicIdException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }
    @ExceptionHandler(ExceedMaximumVotesPerUserException::class)
    fun handle(e: ExceedMaximumVotesPerUserException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }
    @ExceptionHandler(MusicNotFoundException::class)
    fun handle(e: MusicNotFoundException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }
}