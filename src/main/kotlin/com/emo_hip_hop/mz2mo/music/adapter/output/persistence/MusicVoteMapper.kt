package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import com.emo_hip_hop.mz2mo.music.adapter.output.persistence.entity.MusicVoteJpaEntity
import com.emo_hip_hop.mz2mo.music.domain.MusicVote

fun MusicVote.toEntity(id: String): MusicVoteJpaEntity =
    MusicVoteJpaEntity(
        id = id,
        musicId = musicId.id,
        accountId = accountId.id,
        emojiId = emojiId.id,
    )