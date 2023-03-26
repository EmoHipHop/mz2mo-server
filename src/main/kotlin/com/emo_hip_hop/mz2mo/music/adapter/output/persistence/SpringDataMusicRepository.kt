package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface SpringDataMusicRepository: JpaRepository<MusicJpaEntity, String> {
    fun existsByYoutubeId(youtubeId: String): Boolean
}