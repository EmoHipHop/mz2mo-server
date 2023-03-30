package com.emo_hip_hop.mz2mo.music.adapter.output.persistence.repository

import com.emo_hip_hop.mz2mo.music.adapter.output.persistence.entity.MusicVoteJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataMusicVoteRepository : JpaRepository<MusicVoteJpaEntity, String> {
    fun findAllByMusicId(musicId: String): List<MusicVoteJpaEntity>
}