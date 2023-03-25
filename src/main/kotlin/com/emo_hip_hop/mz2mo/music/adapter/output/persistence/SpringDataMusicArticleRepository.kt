package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import org.springframework.data.mongodb.repository.MongoRepository

interface SpringDataMusicArticleRepository: MongoRepository<MusicArticleJpaEntity, String> {
    fun findByMusicId(musicId: String): MusicArticleJpaEntity?
}
