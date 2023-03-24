package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import com.emo_hip_hop.mz2mo.global.PersistenceAdapter
import com.emo_hip_hop.mz2mo.music.application.port.output.CreateMusicPort
import com.emo_hip_hop.mz2mo.music.application.port.output.ExistsMusicPort
import com.emo_hip_hop.mz2mo.music.domain.Music

@PersistenceAdapter
class MusicPersistenceAdapter(
    private val musicRepository: SpringDataMusicRepository,
): CreateMusicPort, ExistsMusicPort {
    override fun create(music: Music): Music {
        val entity = music.toEntity()
        val savedEntity = musicRepository.save(entity)
        return savedEntity.toDomain()
    }

    override fun byYoutubeId(youtubeId: String): Boolean {
        return musicRepository.existsByYoutubeId(youtubeId)
    }

}