package com.emo_hip_hop.mz2mo.music.domain.exception

import com.emo_hip_hop.mz2mo.account.domain.AccountId
import com.emo_hip_hop.mz2mo.emoji.domain.EmojiId
import com.emo_hip_hop.mz2mo.music.domain.MusicId

class MusicVoteNotFoundException(
    musicId: MusicId,
    accountId: AccountId,
    emojiId: EmojiId
) : RuntimeException("vote not found! - $accountId: '${accountId.id}', $emojiId: '${emojiId.id}', $musicId: '${musicId.id}")