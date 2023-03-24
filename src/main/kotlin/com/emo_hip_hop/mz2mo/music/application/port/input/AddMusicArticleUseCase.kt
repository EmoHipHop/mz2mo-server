package com.emo_hip_hop.mz2mo.music.application.port.input

import com.emo_hip_hop.mz2mo.music.domain.MusicArticle

interface AddMusicArticleUseCase {
    operator fun invoke(command: AddMusicArticleCommand): MusicArticle
}