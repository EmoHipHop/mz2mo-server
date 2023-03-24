package com.emo_hip_hop.mz2mo.music.domain

data class MusicArticle (
    val uuid: String?,
    val music: Music,
    val votes: MusicVotes
) {
    fun addVote(vote: MusicVote) =
        MusicArticle(uuid, music, votes.with(vote))

    fun removeVote(vote: MusicVote) =
        MusicArticle(uuid, music, votes.without(vote))

    companion object {
        fun withoutId(savedMusic: Music, empty: MusicVotes): MusicArticle = MusicArticle(null, savedMusic, empty)
    }
}