package com.emo_hip_hop.mz2mo.music.application.port.output

interface ExistsMusicPort {
    fun byYoutubeId(youtubeId: String): Boolean
}
