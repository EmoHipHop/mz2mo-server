package com.emo_hip_hop.mz2mo.music.adapter.input.web

data class MusicArticleResponse(
    val id: String,
    val youtubeId: String,
    val votes: List<MusicVoteResponse>
)