package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import com.emo_hip_hop.mz2mo.global.PersistenceAdapter
import com.emo_hip_hop.mz2mo.music.adapter.output.persistence.entity.MusicVoteJpaEntity
import com.emo_hip_hop.mz2mo.music.adapter.output.persistence.repository.SpringDataMusicVoteRepository
import com.emo_hip_hop.mz2mo.music.application.port.output.QueryMusicVotePort
import com.emo_hip_hop.mz2mo.music.domain.MusicId
import com.emo_hip_hop.mz2mo.music.domain.MusicVotes

@PersistenceAdapter
class MusicVotePersistenceAdapter(
    private val musicVoteRepository: SpringDataMusicVoteRepository,
) : QueryMusicVotePort {
    override fun invoke(musicId: MusicId): MusicVotes {
        val votes = musicVoteRepository.findAllByMusicId(musicId.id)
            .map(MusicVoteJpaEntity::toDomain)
        return MusicVotes.of(votes)
    }
}