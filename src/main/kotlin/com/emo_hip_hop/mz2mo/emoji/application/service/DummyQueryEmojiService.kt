package com.emo_hip_hop.mz2mo.emoji.application.service

import com.emo_hip_hop.mz2mo.emoji.application.port.input.QueryEmojiUseCase
import com.emo_hip_hop.mz2mo.emoji.domain.Emoji
import com.emo_hip_hop.mz2mo.emoji.domain.EmojiId
import com.emo_hip_hop.mz2mo.global.UseCase

@UseCase
class DummyQueryEmojiService : QueryEmojiUseCase {
    override fun invoke(rawEmoji: String): Emoji {
        val uuid = rawEmoji
        return Emoji(EmojiId(uuid), "dummy-emoji", rawEmoji)
    }
}