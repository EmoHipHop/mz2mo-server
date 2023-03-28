package com.emo_hip_hop.mz2mo.music.application.port.input

import com.emo_hip_hop.mz2mo.music.domain.MusicCommunity

interface AddMusicVoteUseCase {
    operator fun invoke(command: AddMusicVoteCommand): MusicCommunity
}