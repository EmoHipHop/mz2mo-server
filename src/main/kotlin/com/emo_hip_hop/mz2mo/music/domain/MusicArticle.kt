package com.emo_hip_hop.mz2mo.music.domain

import java.util.*

data class MusicArticle(
    private val _id: MusicArticleId?,
    val youtubeId: String,
    val votes: MusicVotes
) {
    val id: Optional<MusicArticleId> = Optional.ofNullable(_id)

    companion object {
        fun withId(id: MusicArticleId, youtubeId: String, votes: MusicVotes) =
            MusicArticle(id, youtubeId, votes)

        fun withoutId(youtubeId: String, votes: MusicVotes) =
            MusicArticle(null, youtubeId, votes)
    }

    fun addVote(vote: MusicVote) =
        MusicArticle(_id, youtubeId, votes.with(vote))

    fun removeVote(vote: MusicVote) =
        MusicArticle(_id, youtubeId, votes.without(vote))
}