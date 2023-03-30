package com.emo_hip_hop.mz2mo.music.adapter.input.web.response

import com.emo_hip_hop.mz2mo.global.validate.domain.UUID_EXAMPLE
import com.emo_hip_hop.mz2mo.global.validate.domain.UUID_PATTERN
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Pattern

@Schema(name = "MusicCommunityResponse", description = "음악 커뮤니티 응답")
data class MusicCommunityResponse(
    @Schema(description = "음악 커뮤니티 ID", example = UUID_EXAMPLE)
    @Pattern(regexp = UUID_PATTERN, message = "음악 커뮤니티 ID 형식이 올바르지 않습니다.")
    val id: String,
    @Schema(description = "유튜브 ID", example = "dQw4w9WgXcQ")
    val youtubeId: String,
    @Schema(description = "이모지 투표 목록")
    val votes: List<MusicVoteResponse>
)