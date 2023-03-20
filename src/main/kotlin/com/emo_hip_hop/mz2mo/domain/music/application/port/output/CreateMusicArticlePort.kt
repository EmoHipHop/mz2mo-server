package com.emo_hip_hop.mz2mo.domain.music.application.port.output

import com.emo_hip_hop.mz2mo.domain.music.domain.MusicArticle

interface CreateMusicArticlePort {
    fun create(musicArticle: MusicArticle): MusicArticle
}
