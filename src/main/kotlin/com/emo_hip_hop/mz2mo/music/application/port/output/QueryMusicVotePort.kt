package com.emo_hip_hop.mz2mo.music.application.port.output

import com.emo_hip_hop.mz2mo.music.domain.MusicId
import com.emo_hip_hop.mz2mo.music.domain.MusicVotes

interface QueryMusicVotePort {
    operator fun invoke(musicId: MusicId): MusicVotes
}