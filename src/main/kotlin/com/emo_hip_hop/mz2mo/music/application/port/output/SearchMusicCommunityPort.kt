package com.emo_hip_hop.mz2mo.music.application.port.output

import com.emo_hip_hop.mz2mo.global.common.domain.Pageable
import com.emo_hip_hop.mz2mo.music.domain.MusicCommunity

interface SearchMusicCommunityPort {
    fun search(pageable: Pageable): List<MusicCommunity>
}
