package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import com.emo_hip_hop.mz2mo.global.common.domain.Pageable

interface CustomMusicCommunityRepository {
    fun search(pageable: Pageable): List<MusicCommunitiesJpaEntity>
}