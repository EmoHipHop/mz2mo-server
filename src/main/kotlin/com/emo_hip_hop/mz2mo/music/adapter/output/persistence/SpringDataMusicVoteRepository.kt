package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import org.springframework.data.mongodb.repository.MongoRepository

interface SpringDataMusicVoteRepository: MongoRepository<MusicVoteJpaEntity, String> {
    fun findAllByMusicId(musicId: String): List<MusicVoteJpaEntity>
}
