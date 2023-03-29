package com.emo_hip_hop.mz2mo.music.application.port.output

import com.emo_hip_hop.mz2mo.music.domain.MusicCommunity
import com.emo_hip_hop.mz2mo.music.domain.MusicId

interface QueryMusicCommunityPort {
    fun findByMusicId(id: MusicId): MusicCommunity?
}