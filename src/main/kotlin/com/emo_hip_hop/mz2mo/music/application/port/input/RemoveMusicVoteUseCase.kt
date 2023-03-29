package com.emo_hip_hop.mz2mo.music.application.port.input

interface RemoveMusicVoteUseCase {
    operator fun invoke(command: RemoveMusicVoteCommand)
}