package com.emo_hip_hop.mz2mo.music.domain

class MusicVotes private constructor(votes: List<MusicVote>): List<MusicVote> by votes {
    companion object {
        fun empty() = MusicVotes(emptyList())
        fun of(votes: List<MusicVote>) = MusicVotes(votes)
    }

    fun with(vote: MusicVote): MusicVotes {
        val votes = toMutableList()
        votes.add(vote)
        return MusicVotes(votes)
    }

    fun without(vote: MusicVote): MusicVotes {
        val votes = toMutableList()
        votes.remove(vote)
        return MusicVotes(votes)
    }
}