package com.emo_hip_hop.mz2mo.music.adapter.input.web

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "MusicCommunitiesResponse", description = "음악 커뮤니티 목록 응답")
data class MusicCommunitiesResponse(
    @Schema(description = "목록 사이즈")
    val musicCommunities: Int,
    @Schema(description = "음악 커뮤니티 목록")
    val list: List<MusicCommunityResponse>
)