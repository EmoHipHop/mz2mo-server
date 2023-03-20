package com.emo_hip_hop.mz2mo.domain.music.application.port.input

import com.emo_hip_hop.mz2mo.domain.music.domain.MusicArticle

interface AddMusicArticleUseCase {
    operator fun invoke(command: AddMusicCommand): MusicArticle
}