package com.emo_hip_hop.mz2mo.music.domain

import com.emo_hip_hop.mz2mo.emoji.domain.EmojiId

data class MusicVotePercentage(
    val musicId: MusicId,
    val emojiId: EmojiId,
    val percentage: Double
)