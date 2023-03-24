package com.emo_hip_hop.mz2mo.music.domain

import java.util.*

data class Music(
    private val _id: MusicId?,
    val youtubeId: String
) {
    val id: Optional<MusicId> = Optional.ofNullable(_id)
    companion object {
        fun withId(id: MusicId, youtubeId: String) =
            Music(id, youtubeId)

        fun withoutId(youtubeId: String) =
            Music(null, youtubeId)
    }
}