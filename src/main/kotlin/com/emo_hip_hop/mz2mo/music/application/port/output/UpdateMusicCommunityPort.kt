package com.emo_hip_hop.mz2mo.music.application.port.output

import com.emo_hip_hop.mz2mo.music.domain.MusicCommunity

interface UpdateMusicCommunityPort {
    fun update(musicCommunity: MusicCommunity): MusicCommunity
}