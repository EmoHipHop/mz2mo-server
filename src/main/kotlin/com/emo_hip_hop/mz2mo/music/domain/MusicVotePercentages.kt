package com.emo_hip_hop.mz2mo.music.domain

class MusicVotePercentages private constructor(votes: List<MusicVotePercentage>) : List<MusicVotePercentage> by votes {
    companion object {
        fun empty() = MusicVotePercentages(emptyList())
        fun of(votes: List<MusicVotePercentage>) = MusicVotePercentages(votes)
    }
}