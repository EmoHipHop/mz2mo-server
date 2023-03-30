package com.emo_hip_hop.mz2mo.music.adapter.input.web.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "AddMusicVoteRequest", description = "음악 투표 삭제 요청")
data class RemoveMusicVoteRequest(
    @Schema(description = "이모지", example = "💡")
    val rawEmoji: String
)