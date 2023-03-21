package com.emo_hip_hop.mz2mo.music.adaptor.output

import com.emo_hip_hop.mz2mo.account.domain.AccountId
import com.emo_hip_hop.mz2mo.emoji.domain.EmojiId
import com.emo_hip_hop.mz2mo.music.domain.MusicArticle
import com.emo_hip_hop.mz2mo.music.domain.MusicArticleId
import com.emo_hip_hop.mz2mo.music.domain.MusicVote
import com.emo_hip_hop.mz2mo.music.domain.MusicVotes
import org.bson.types.ObjectId

fun MusicArticle.toEntity(): MusicArticleJpaEntity {
    return MusicArticleJpaEntity(
        id = id.orElse(null)?.let{ ObjectId(it.id) },
        youtubeTrackId = youtubeId,
    )
}

fun MusicArticleJpaEntity.toDomain(votes: List<MusicVoteJpaEntity>): MusicArticle {
    return MusicArticle(
        _id = id?.let { MusicArticleId(it.toString()) },
        youtubeId = youtubeTrackId,
        votes = votes.map { it.toDomain() }.let { MusicVotes.of(it) }
    )
}

private fun MusicVoteJpaEntity.toDomain(): MusicVote {
    return MusicVote(
        accountId = AccountId(accountId),
        emojiId = EmojiId(emojiId)
    )
}