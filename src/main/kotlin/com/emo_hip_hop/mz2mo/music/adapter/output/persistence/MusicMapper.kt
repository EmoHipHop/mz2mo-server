package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import com.emo_hip_hop.mz2mo.music.domain.Music
import com.emo_hip_hop.mz2mo.music.domain.MusicId
import org.bson.types.ObjectId

fun Music.toEntity(): MusicJpaEntity =
    MusicJpaEntity(
        id = id.orElse(null)?.let{ObjectId(it.id)},
        youtubeId = youtubeId,
    )

fun MusicJpaEntity.toDomain(): Music =
    if(id == null) Music.withoutId(youtubeId)
    else Music.withId(MusicId(id.toString()), youtubeId)