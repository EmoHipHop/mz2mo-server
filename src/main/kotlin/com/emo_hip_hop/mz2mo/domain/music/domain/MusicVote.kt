package com.emo_hip_hop.mz2mo.domain.music.domain

import com.emo_hip_hop.mz2mo.domain.account.domain.AccountId
import com.emo_hip_hop.mz2mo.domain.emoji.domain.Emoji

data class MusicVote(
    val accountId: AccountId,
    val emoji: Emoji
)