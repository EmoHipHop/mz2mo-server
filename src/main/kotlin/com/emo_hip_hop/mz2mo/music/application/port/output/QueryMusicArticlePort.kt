package com.emo_hip_hop.mz2mo.music.application.port.output

import com.emo_hip_hop.mz2mo.music.domain.MusicArticle
import com.emo_hip_hop.mz2mo.music.domain.MusicId

interface QueryMusicArticlePort {
    fun findByMusicId(id: MusicId): MusicArticle?
}
