package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataMusicVoteRepository: JpaRepository<MusicVoteJpaEntity, String> {
    fun findAllByMusicId(musicId: String): List<MusicVoteJpaEntity>
}
