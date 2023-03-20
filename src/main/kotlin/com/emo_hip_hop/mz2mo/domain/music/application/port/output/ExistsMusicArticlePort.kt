package com.emo_hip_hop.mz2mo.domain.music.application.port.output

interface ExistsMusicArticlePort {
    fun byId(youtubeTrackId: String): Boolean
}
