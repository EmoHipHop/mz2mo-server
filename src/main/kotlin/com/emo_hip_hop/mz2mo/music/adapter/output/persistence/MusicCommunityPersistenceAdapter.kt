package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import com.emo_hip_hop.mz2mo.global.PersistenceAdapter
import com.emo_hip_hop.mz2mo.global.common.domain.Pageable
import com.emo_hip_hop.mz2mo.music.adapter.output.persistence.entity.MusicCommunitiesJpaEntity
import com.emo_hip_hop.mz2mo.music.adapter.output.persistence.entity.MusicVoteJpaEntity
import com.emo_hip_hop.mz2mo.music.adapter.output.persistence.repository.SpringDataCustomMusicCommunityRepository
import com.emo_hip_hop.mz2mo.music.adapter.output.persistence.repository.SpringDataMusicRepository
import com.emo_hip_hop.mz2mo.music.adapter.output.persistence.repository.SpringDataMusicVoteRepository
import com.emo_hip_hop.mz2mo.music.application.port.output.CreateMusicCommunityPort
import com.emo_hip_hop.mz2mo.music.application.port.output.QueryMusicCommunityPort
import com.emo_hip_hop.mz2mo.music.application.port.output.SearchMusicCommunityPort
import com.emo_hip_hop.mz2mo.music.application.port.output.UpdateMusicCommunityPort
import com.emo_hip_hop.mz2mo.music.domain.*
import com.emo_hip_hop.mz2mo.music.domain.exception.EmptyMusicIdException
import com.emo_hip_hop.mz2mo.music.domain.exception.MusicNotFoundException
import com.emo_hip_hop.mz2mo.music.domain.exception.MusicOutOfSyncException
import java.util.*

@PersistenceAdapter
class MusicCommunityPersistenceAdapter(
    private val musicCommunityRepository: SpringDataCustomMusicCommunityRepository,
    private val musicVoteRepository: SpringDataMusicVoteRepository,
    private val musicRepository: SpringDataMusicRepository
) : CreateMusicCommunityPort, UpdateMusicCommunityPort, QueryMusicCommunityPort, SearchMusicCommunityPort {
    override fun create(domain: MusicCommunity): MusicCommunity {
        val id = UUID.randomUUID()
        val entityToAdd = domain.toEntity()
        if (entityToAdd.id == null) entityToAdd.id = id.toString()
        println("uuid is ${entityToAdd.id}")
        val musicCommunity = musicCommunityRepository.save(entityToAdd)
        return aggregateMusicCommunity(musicCommunity)
    }

    //💡In addition to MusicVote and Music, updates may occur for other Domains in MusicCommunity later.
    override fun update(musicCommunity: MusicCommunity): MusicCommunity {
        val musicId = musicCommunity.music.id.orElseThrow { EmptyMusicIdException() }

        if (!musicCommunityRepository.existsByMusicId(musicId.id)) throw MusicOutOfSyncException("id", musicId.id, syncTo = "musicCommunity")
        if (!musicRepository.existsById(musicId.id)) throw MusicNotFoundException("id", musicId.id)

        val musicToAdd = musicCommunity.music.toEntity()
        musicRepository.save(musicToAdd)

        val existsMusicVoteEntities = musicVoteRepository.findAllByMusicId(musicId.id)

        /* 💡Venn Diagram for update vote
        - (inEntity), [inDomain]
        - (in Entity but outed Domain[
        - )in Domain but outed Entity]
        ➡️ (votesToDelete [VotesOutOfAllScopes) votesToAdd]
         */
        val votesToDelete = existsInEntityBytNotExistsInDomain(existsMusicVoteEntities, musicCommunity.votes)
        musicVoteRepository.deleteAllById(votesToDelete.map { it.id })

        val votesToAdd = existsInDomainBytNotExistsInEntity(musicId, existsMusicVoteEntities, musicCommunity.votes)
        musicVoteRepository.saveAll(votesToAdd)
        return findByMusicId(musicId)!!
    }
    fun existsInEntityBytNotExistsInDomain(
        entities: List<MusicVoteJpaEntity>,
        domains: MusicVotes
    ): List<MusicVoteJpaEntity> =
        entities.filter { entity -> domains.none { domain -> compareWithoutId(domain, entity) } }

    fun existsInDomainBytNotExistsInEntity(
        musicId: MusicId,
        entities: List<MusicVoteJpaEntity>,
        domains: MusicVotes
    ): List<MusicVoteJpaEntity> =
        domains.filter { domain -> entities.none { entity -> compareWithoutId(domain, entity) } }
            .map { it.toEntity() }

    private fun compareWithoutId(domain: MusicVote, entity: MusicVoteJpaEntity): Boolean =
        domain.emojiId.id == entity.emojiId && domain.musicId.id == entity.musicId && domain.accountId.id == entity.accountId

    override fun findByMusicId(id: MusicId): MusicCommunity? {
        val musicCommunity = musicCommunityRepository.findByMusicId(id.id) ?: return null
        return aggregateMusicCommunity(musicCommunity)
    }

    private fun aggregateMusicCommunity(
        musicCommunity: MusicCommunitiesJpaEntity,
    ): MusicCommunity {
        val musicId = musicCommunity.musicId
        val votes = musicVoteRepository.findAllByMusicId(musicId)
        val music = musicRepository.findById(musicId)
            .orElseThrow { MusicOutOfSyncException("id", musicId, syncTo = "musicCommunity") }

        return musicCommunity.toDomain(votes, music)
    }

    override fun search(pageable: Pageable): List<MusicCommunity> {
        val musicCommunities = musicCommunityRepository.search(pageable)
        return musicCommunities.map { aggregateMusicCommunity(it) }
    }
}