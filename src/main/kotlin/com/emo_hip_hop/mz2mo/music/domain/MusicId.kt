package com.emo_hip_hop.mz2mo.music.domain

import java.util.*

data class MusicId(
    val id: String
)

fun Optional<MusicId>.idOrEmpty(): String =
    if (isPresent) get().id else ""