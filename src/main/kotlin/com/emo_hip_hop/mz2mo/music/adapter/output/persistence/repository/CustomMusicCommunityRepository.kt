package com.emo_hip_hop.mz2mo.music.adapter.output.persistence.repository

import com.emo_hip_hop.mz2mo.global.common.domain.Pageable
import com.emo_hip_hop.mz2mo.music.adapter.output.persistence.entity.MusicCommunitiesJpaEntity

interface CustomMusicCommunityRepository {
    fun search(pageable: Pageable): List<MusicCommunitiesJpaEntity>
}