package com.emo_hip_hop.mz2mo.music.adapter.output.persistence.repository

import com.emo_hip_hop.mz2mo.music.adapter.output.persistence.entity.MusicJpaEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataMusicRepository : JpaRepository<MusicJpaEntity, String> {
    fun existsByYoutubeId(youtubeId: String): Boolean
}