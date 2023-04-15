package com.emo_hip_hop.mz2mo.music.adapter.input.web.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "MusicVotePercentagesResponse", description = "음악 투표율 목록 응답")
data class MusicVotePercentagesResponse(
    @Schema(description = "목록 사이즈")
    val size: Int = 0,
    @Schema(description = "음악 커뮤니티 목록")
    val list: List<MusicVotePercentageResponse>
)