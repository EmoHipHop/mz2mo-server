package com.emo_hip_hop.mz2mo.music.adapter.input.web

import com.emo_hip_hop.mz2mo.music.domain.MusicArticle
import com.emo_hip_hop.mz2mo.music.domain.MusicVotes

fun MusicArticle.toResponse(): MusicArticleResponse {
    val id = id.let { if (it.isPresent) it.get().id else "" }
    return MusicArticleResponse(
        id = id,
        youtubeId = youtubeId,
        votes = votes.toResponse(id)
    )
}

//TODO MusicVote 및 MusicArticle 모두 musicId 를 참조하게 변경하기
private fun MusicVotes.toResponse(articleId: String): List<MusicVoteResponse> = map {
    MusicVoteResponse(
        articleId = articleId,
        accountId = it.accountId.id,
        emojiId = it.emojiId.id
    )
}