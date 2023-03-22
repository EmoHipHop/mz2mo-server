package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import com.emo_hip_hop.mz2mo.global.PersistenceAdapter
import com.emo_hip_hop.mz2mo.music.application.port.output.CreateMusicArticlePort
import com.emo_hip_hop.mz2mo.music.domain.MusicArticle

@PersistenceAdapter
class MusicArticlePersistenceAdaptor(
    private val musicArticleRepository: SpringDataMusicArticleRepository,
    private val musicVoteRepository: SpringDataMusicVoteRepository
): CreateMusicArticlePort {
    override fun create(musicArticle: MusicArticle): MusicArticle {
        val entity = musicArticle.toEntity()
        val savedEntity = musicArticleRepository.save(entity)
        val votes = musicVoteRepository.findAllByMusicId(savedEntity.musicId)
        val youtubeId = musicArticle.music.youtubeId
        return savedEntity.toDomain(votes, youtubeId)
    }
}