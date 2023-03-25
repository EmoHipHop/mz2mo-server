package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import com.emo_hip_hop.mz2mo.global.PersistenceAdapter
import com.emo_hip_hop.mz2mo.music.application.port.output.CreateMusicCommunityPort
import com.emo_hip_hop.mz2mo.music.application.port.output.QueryMusicCommunityPort
import com.emo_hip_hop.mz2mo.music.domain.MusicCommunity
import com.emo_hip_hop.mz2mo.music.domain.MusicId
import com.emo_hip_hop.mz2mo.music.domain.MusicOutOfSyncException

@PersistenceAdapter
class MusicCommunityPersistenceAdapter(
    private val musicCommunityRepository: SpringDataMusicCommunityRepository,
    private val musicVoteRepository: SpringDataMusicVoteRepository,
    private val musicRepository: SpringDataMusicRepository
): CreateMusicCommunityPort, QueryMusicCommunityPort {
    override fun create(domain: MusicCommunity): MusicCommunity {
        val entityToAdd = domain.toEntity()
        val musicCommunity = musicCommunityRepository.save(entityToAdd)
        return aggregateMusicCommunity(musicCommunity)
    }

    override fun findByMusicId(id: MusicId): MusicCommunity? {
        val musicCommunity = musicCommunityRepository.findByMusicId(id.id) ?: return null
        return aggregateMusicCommunity(musicCommunity)
    }

    fun aggregateMusicCommunity(
        musicCommunity: MusicCommunitiesJpaEntity,
    ): MusicCommunity {
        val musicId = musicCommunity.musicId
        val votes = musicVoteRepository.findAllByMusicId(musicId)
        val music = musicRepository.findById(musicId)
            .orElseThrow{ MusicOutOfSyncException("id", musicId, syncTo = "musicCommunity") }

        return musicCommunity.toDomain(votes, music)
    }
}