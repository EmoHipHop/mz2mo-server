package com.emo_hip_hop.mz2mo.music.adapter.input.web.request

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "AddMusicCommunityRequest", description = "음악 커뮤니티 추가 요청")
data class AddMusicCommunityRequest(
    @Schema(description = "유튜브 ID", example = "dQw4w9WgXcQ")
    val youtubeId: String
)