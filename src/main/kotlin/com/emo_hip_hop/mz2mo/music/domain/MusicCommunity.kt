package com.emo_hip_hop.mz2mo.music.domain

data class MusicCommunity (
    val uuid: String?,
    val music: Music,
    val votes: MusicVotes
) {
    fun addVote(vote: MusicVote) =
        MusicCommunity(uuid, music, votes.with(vote))

    fun removeVote(vote: MusicVote) =
        MusicCommunity(uuid, music, votes.without(vote))

    companion object {
        fun withoutId(savedMusic: Music, empty: MusicVotes): MusicCommunity = MusicCommunity(null, savedMusic, empty)
    }
}