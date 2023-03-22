package com.emo_hip_hop.mz2mo.music.adapter.input.web

import com.emo_hip_hop.mz2mo.music.domain.*

fun MusicArticle.toResponse(): MusicArticleResponse {
    val id = music.id.idOrEmpty()
    return MusicArticleResponse(
        id = id,
        youtubeId = music.youtubeId,
        votes = votes.toResponse()
    )
}

private fun MusicVotes.toResponse(): List<MusicVoteResponse> = map { vote ->
    MusicVoteResponse(
        articleId = vote.musicId.id,
        accountId = vote.accountId.id,
        emojiId = vote.emojiId.id
    )
}