package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import org.springframework.data.mongodb.repository.MongoRepository

interface SpringDataMusicCommunityRepository: MongoRepository<MusicCommunitiesJpaEntity, String> {
    fun findByMusicId(musicId: String): MusicCommunitiesJpaEntity?
}
