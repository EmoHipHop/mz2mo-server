package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import com.emo_hip_hop.mz2mo.global.PersistenceAdapter
import com.emo_hip_hop.mz2mo.music.adapter.output.persistence.repository.SpringDataMusicRepository
import com.emo_hip_hop.mz2mo.music.application.port.output.CreateMusicPort
import com.emo_hip_hop.mz2mo.music.application.port.output.ExistsMusicPort
import com.emo_hip_hop.mz2mo.music.domain.Music
import java.util.*

@PersistenceAdapter
class MusicPersistenceAdapter(
    private val musicRepository: SpringDataMusicRepository,
): CreateMusicPort, ExistsMusicPort {
    override fun create(music: Music): Music {
        val id = UUID.randomUUID()
        val entityToAdd = music.toEntity()
        if(entityToAdd.id == null) entityToAdd.id = id.toString()
        println("uuid is ${entityToAdd.id}")
        val savedEntity = musicRepository.save(entityToAdd)
        return savedEntity.toDomain()
    }

    override fun byYoutubeId(youtubeId: String): Boolean {
        return musicRepository.existsByYoutubeId(youtubeId)
    }

}