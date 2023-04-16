package com.emo_hip_hop.mz2mo.music.adapter.input.web.response

import com.emo_hip_hop.mz2mo.global.validate.domain.UUID_EXAMPLE
import com.emo_hip_hop.mz2mo.global.validate.domain.UUID_PATTERN
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Pattern

@Schema(name = "MusicVotePercentageResponse", description = "음악 투표율 응답")
data class MusicVotePercentageResponse(
    @Schema(description = "음악 ID", example = UUID_EXAMPLE)
    @Pattern(regexp = UUID_PATTERN, message = "음악 ID 형식이 올바르지 않습니다.")
    val musicId: String,
    @Schema(description = "이모지 ID", example = UUID_EXAMPLE)
    @Pattern(regexp = UUID_PATTERN, message = "이모지 ID 형식이 올바르지 않습니다.")
    val emojiId: String,
    @Schema(description = "투표율", example = "28.53")
    val percentage: Double,
)