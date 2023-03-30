package com.emo_hip_hop.mz2mo.music.application.port.output

import com.emo_hip_hop.mz2mo.music.domain.Music

interface CreateMusicPort {
    fun create(music: Music): Music
}