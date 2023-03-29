package com.emo_hip_hop.mz2mo.music.application.port.output

import com.emo_hip_hop.mz2mo.music.domain.MusicCommunity

interface CreateMusicCommunityPort {
    fun create(domain: MusicCommunity): MusicCommunity
}