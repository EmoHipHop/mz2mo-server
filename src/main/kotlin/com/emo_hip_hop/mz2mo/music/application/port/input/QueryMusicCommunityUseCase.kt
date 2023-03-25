package com.emo_hip_hop.mz2mo.music.application.port.input

import com.emo_hip_hop.mz2mo.music.domain.MusicCommunity
import com.emo_hip_hop.mz2mo.music.domain.MusicId

interface QueryMusicCommunityUseCase {
    operator fun invoke(musicId: MusicId): MusicCommunity
}