package com.emo_hip_hop.mz2mo.music.adapter.input.web.response

import com.emo_hip_hop.mz2mo.global.swagger.UUID_EXAMPLE
import com.emo_hip_hop.mz2mo.global.swagger.UUID_PATTERN
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Pattern

@Schema(name = "MusicVoteResponse", description = "음악 투표 응답")
data class MusicVoteResponse (
    @Schema(description = "음악 ID", example = UUID_EXAMPLE)
    val musicId: String,
    @Schema(description = "계정 ID", example = UUID_EXAMPLE)
    @Pattern(regexp = UUID_PATTERN, message = "계정 ID 형식이 올바르지 않습니다.")
    val accountId: String,
    @Schema(description = "이모지 ID", example = UUID_EXAMPLE)
    @Pattern(regexp = UUID_PATTERN, message = "이모지 ID 형식이 올바르지 않습니다.")
    val emojiId: String,
)