package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataCustomMusicCommunityRepository: JpaRepository<MusicCommunitiesJpaEntity, String>, CustomMusicCommunityRepository {
    fun findByMusicId(musicId: String): MusicCommunitiesJpaEntity?
}
