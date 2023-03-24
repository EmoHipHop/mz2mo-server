package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import com.emo_hip_hop.mz2mo.global.PersistenceAdapter
import com.emo_hip_hop.mz2mo.music.application.port.output.CreateMusicArticlePort
import com.emo_hip_hop.mz2mo.music.application.port.output.QueryMusicArticlePort
import com.emo_hip_hop.mz2mo.music.domain.MusicArticle
import com.emo_hip_hop.mz2mo.music.domain.MusicId
import com.emo_hip_hop.mz2mo.music.domain.MusicNotFoundException

@PersistenceAdapter
class MusicArticlePersistenceAdaptor(
    private val musicArticleRepository: SpringDataMusicArticleRepository,
    private val musicVoteRepository: SpringDataMusicVoteRepository,
    private val musicRepository: SpringDataMusicRepository
): CreateMusicArticlePort, QueryMusicArticlePort {
    override fun create(musicArticle: MusicArticle): MusicArticle {
        val entity = musicArticle.toEntity()
        val savedEntity = musicArticleRepository.save(entity)
        val votes = musicVoteRepository.findAllByMusicId(savedEntity.musicId)
        val youtubeId = musicArticle.music.youtubeId
        return savedEntity.toDomain(votes, youtubeId)
    }

    override fun findByMusicId(id: MusicId): MusicArticle? {
        val musicArticle = musicArticleRepository.findByMusicId(id.id) ?: return null
        val musicId = musicArticle.musicId

        val votes = musicVoteRepository.findAllByMusicId(musicId)
        val music = musicRepository.findById(musicId)
            .orElseThrow{ MusicNotFoundException("musicId", musicId) }

        return aggregateMusicArticle(musicArticle, votes, music)
    }
}