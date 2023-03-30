package com.emo_hip_hop.mz2mo.music.adapter.input.web

import com.emo_hip_hop.mz2mo.global.error.adapter.input.web.ErrorResponse
import com.emo_hip_hop.mz2mo.global.error.domain.ErrorCode
import com.emo_hip_hop.mz2mo.music.domain.*
import com.emo_hip_hop.mz2mo.music.domain.exception.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackageClasses = [MusicCommunityController::class, MusicVoteController::class])
class MusicControllerAdvice {
    @ExceptionHandler(MusicAlreadyExistsException::class)
    fun handle(e: MusicAlreadyExistsException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse.of(ErrorCode.MUSIC_ALREADY_EXISTS, e.message)
        return ResponseEntity.badRequest().body(response)
    }
    @ExceptionHandler(MusicOutOfSyncException::class)
    fun handle(e: MusicOutOfSyncException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse.of(ErrorCode.MUSIC_OUT_OF_SYNC, e.message)
        return ResponseEntity.internalServerError().body(response)
    }
    @ExceptionHandler(AlreadyVoteException::class)
    fun handle(e: AlreadyVoteException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse.of(ErrorCode.ALREADY_VOTE, e.message)
        return ResponseEntity.badRequest().body(response)
    }
    @ExceptionHandler(EmptyMusicIdException::class)
    fun handle(e: EmptyMusicIdException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse.of(ErrorCode.EMPTY_MUSIC_ID, e.message)
        return ResponseEntity.badRequest().body(response)
    }
    @ExceptionHandler(ExceedMaximumVotesPerUserException::class)
    fun handle(e: ExceedMaximumVotesPerUserException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse.of(ErrorCode.EXCEED_MAXIMUM_VOTE_PER_USER, e.message)
        return ResponseEntity.badRequest().body(response)
    }
    @ExceptionHandler(MusicCommunityOrPartialNotFoundException::class)
    fun handle(e: MusicCommunityOrPartialNotFoundException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse.of(ErrorCode.MUSIC_COMMUNITY_OR_PARTIAL_NOT_FOUND, e.message)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
    }
    @ExceptionHandler(MusicNotFoundException::class)
    fun handle(e: MusicNotFoundException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse.of(ErrorCode.MUSIC_NOT_FOUND, e.message)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
    }
    @ExceptionHandler(MusicVoteNotFoundException::class)
    fun handle(e: MusicVoteNotFoundException): ResponseEntity<ErrorResponse> {
        val response = ErrorResponse.of(ErrorCode.MUSIC_VOTE_NOT_FOUND, e.message)
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
    }
}