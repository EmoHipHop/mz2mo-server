package com.emo_hip_hop.mz2mo.music.application.port.output

interface ExistsMusicArticlePort {
    fun byId(youtubeTrackId: String): Boolean
}
