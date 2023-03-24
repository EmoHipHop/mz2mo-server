package com.emo_hip_hop.mz2mo.music.domain

import com.emo_hip_hop.mz2mo.account.domain.AccountId
import com.emo_hip_hop.mz2mo.emoji.domain.EmojiId

data class MusicVote(
    val uuid: String?,
    val musicId: MusicId,
    val accountId: AccountId,
    val emojiId: EmojiId
)