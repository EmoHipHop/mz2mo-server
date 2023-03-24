package com.emo_hip_hop.mz2mo.music.application.port.output

import com.emo_hip_hop.mz2mo.music.domain.MusicArticle

interface CreateMusicArticlePort {
    fun create(musicArticle: MusicArticle): MusicArticle
}
