package com.emo_hip_hop.mz2mo.music.application.port.input

import com.emo_hip_hop.mz2mo.account.domain.AccountId
import com.emo_hip_hop.mz2mo.emoji.domain.EmojiId
import com.emo_hip_hop.mz2mo.global.validate.domain.SelfValidating
import com.emo_hip_hop.mz2mo.music.domain.MusicId

class AddMusicVoteCommand(
    val musicId: MusicId,
    val accountId: AccountId,
    val emojiId: EmojiId
) : SelfValidating<AddMusicVoteCommand>()