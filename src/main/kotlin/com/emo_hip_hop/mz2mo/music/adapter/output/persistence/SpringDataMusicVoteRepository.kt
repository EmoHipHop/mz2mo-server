package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import org.springframework.data.mongodb.repository.MongoRepository

interface SpringDataMusicVoteRepository: MongoRepository<MusicVoteJpaEntity, String> {
    fun findAllByMusicArticleId(musicArticleId: String): List<MusicVoteJpaEntity>
}
