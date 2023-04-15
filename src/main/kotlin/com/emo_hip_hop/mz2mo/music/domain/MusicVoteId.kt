package com.emo_hip_hop.mz2mo.music.domain

import java.util.*

data class MusicVoteId(
    val id: String
) {
    companion object {
        fun create(): MusicVoteId = MusicVoteId(UUID.randomUUID().toString())
    }
}