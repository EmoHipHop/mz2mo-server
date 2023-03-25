package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import com.emo_hip_hop.mz2mo.global.PersistenceAdapter
import com.emo_hip_hop.mz2mo.music.application.port.output.CreateMusicArticlePort
import com.emo_hip_hop.mz2mo.music.application.port.output.QueryMusicArticlePort
import com.emo_hip_hop.mz2mo.music.domain.MusicArticle
import com.emo_hip_hop.mz2mo.music.domain.MusicId
import com.emo_hip_hop.mz2mo.music.domain.MusicOutOfSyncException

@PersistenceAdapter
class MusicArticlePersistenceAdapter(
    private val musicArticleRepository: SpringDataMusicArticleRepository,
    private val musicVoteRepository: SpringDataMusicVoteRepository,
    private val musicRepository: SpringDataMusicRepository
): CreateMusicArticlePort, QueryMusicArticlePort {
    override fun create(domain: MusicArticle): MusicArticle {
        val entityToAdd = domain.toEntity()
        val musicArticle = musicArticleRepository.save(entityToAdd)
        return aggregateMusicArticle(musicArticle)
    }

    override fun findByMusicId(id: MusicId): MusicArticle? {
        val musicArticle = musicArticleRepository.findByMusicId(id.id) ?: return null
        return aggregateMusicArticle(musicArticle)
    }

    fun aggregateMusicArticle(
        musicArticle: MusicArticleJpaEntity,
    ): MusicArticle {
        val musicId = musicArticle.musicId
        val votes = musicVoteRepository.findAllByMusicId(musicId)
        val music = musicRepository.findById(musicId)
            .orElseThrow{ MusicOutOfSyncException("id", musicId, syncTo = "musicArticle") }

        return musicArticle.toDomain(votes, music)
    }
}