package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import com.emo_hip_hop.mz2mo.account.domain.AccountId
import com.emo_hip_hop.mz2mo.emoji.domain.EmojiId
import com.emo_hip_hop.mz2mo.music.domain.*
import org.bson.types.ObjectId

fun MusicCommunity.toEntity(): MusicCommunitiesJpaEntity {
    return MusicCommunitiesJpaEntity(
        id = uuid?.let { ObjectId(it) },
        musicId = music.id.let { if(it.isPresent) it.get().id else ""  }
    )
}

fun MusicCommunitiesJpaEntity.toDomain(votes: List<MusicVoteJpaEntity>, music: MusicJpaEntity): MusicCommunity {
    return MusicCommunity(
        uuid = id.toString(),
        music = Music.withId(MusicId(musicId), music.youtubeId),
        votes = votes.map { it.toDomain() }.let { MusicVotes.of(it) }
    )
}

private fun MusicVoteJpaEntity.toDomain(): MusicVote {
    return MusicVote(
        uuid = id?.toString(),
        musicId = MusicId(musicId),
        accountId = AccountId(accountId),
        emojiId = EmojiId(emojiId)
    )
}