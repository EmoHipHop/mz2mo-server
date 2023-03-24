package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import org.springframework.data.mongodb.repository.MongoRepository

interface SpringDataMusicRepository: MongoRepository<MusicJpaEntity, String> {
    fun existsByYoutubeId(youtubeId: String): Boolean
}