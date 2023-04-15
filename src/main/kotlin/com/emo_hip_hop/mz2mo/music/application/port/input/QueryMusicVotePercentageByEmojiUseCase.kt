package com.emo_hip_hop.mz2mo.music.application.port.input

import com.emo_hip_hop.mz2mo.music.domain.MusicId
import com.emo_hip_hop.mz2mo.music.domain.MusicVotePercentages

interface QueryMusicVotePercentageByEmojiUseCase {
    operator fun invoke(musicId: MusicId): MusicVotePercentages
}