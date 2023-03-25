package com.emo_hip_hop.mz2mo.music.application.service

import com.emo_hip_hop.mz2mo.global.UseCase
import com.emo_hip_hop.mz2mo.music.application.port.input.QueryMusicArticleUseCase
import com.emo_hip_hop.mz2mo.music.application.port.output.QueryMusicArticlePort
import com.emo_hip_hop.mz2mo.music.domain.MusicArticle
import com.emo_hip_hop.mz2mo.music.domain.MusicArticleNotFoundException
import com.emo_hip_hop.mz2mo.music.domain.MusicId

@UseCase
class QueryMusicArticleService(
    private val queryMusicArticlePort: QueryMusicArticlePort
): QueryMusicArticleUseCase {
    override fun invoke(musicId: MusicId): MusicArticle {
        val articleOrNull = queryMusicArticlePort.findByMusicId(musicId)
        return articleOrNull ?: throw MusicArticleNotFoundException("musicId", musicId.id)
    }
}