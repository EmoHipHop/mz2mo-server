package com.emo_hip_hop.mz2mo.domain.music.domain

import java.util.*

data class MusicArticle(
    private val _id: MusicArticleId?,
    val votes: List<MusicVote>
) {
    val id: Optional<MusicArticleId> = Optional.ofNullable(_id)

    companion object {
        fun withId(id: MusicArticleId, votes: List<MusicVote>) =
            MusicArticle(id, votes)

        fun withoutId(votes: List<MusicVote>) =
            MusicArticle(null, votes)
    }

    fun addVote(vote: MusicVote) =
        MusicArticle(id.orElse(null), votes + vote)

    fun removeVote(vote: MusicVote) =
        MusicArticle(id.orElse(null), votes - vote)
}