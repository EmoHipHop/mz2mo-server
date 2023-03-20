package com.emo_hip_hop.mz2mo.music.domain

import com.emo_hip_hop.mz2mo.account.domain.AccountId
import com.emo_hip_hop.mz2mo.emoji.domain.Emoji

data class MusicVote(
    val accountId: AccountId,
    val emoji: Emoji
)