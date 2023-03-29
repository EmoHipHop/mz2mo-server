package com.emo_hip_hop.mz2mo.emoji.application.port.input

import com.emo_hip_hop.mz2mo.emoji.domain.Emoji

interface QueryEmojiUseCase {
    operator fun invoke(rawEmoji: String): Emoji
}