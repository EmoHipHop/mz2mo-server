package com.emo_hip_hop.mz2mo.music.application.port.input

import com.emo_hip_hop.mz2mo.music.domain.MusicArticle
import com.emo_hip_hop.mz2mo.music.domain.MusicId

interface QueryMusicArticleUseCase {
    operator fun invoke(musicId: MusicId): MusicArticle
}