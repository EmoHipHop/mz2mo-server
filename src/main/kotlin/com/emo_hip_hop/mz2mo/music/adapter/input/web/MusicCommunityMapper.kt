package com.emo_hip_hop.mz2mo.music.adapter.input.web

import com.emo_hip_hop.mz2mo.music.adapter.input.web.response.*
import com.emo_hip_hop.mz2mo.music.domain.*

fun MusicCommunity.toResponse(): MusicCommunityResponse {
    val id = music.id.idOrEmpty()
    return MusicCommunityResponse(
        id = id,
        youtubeId = music.youtubeId,
        votes = votes.toResponse()
    )
}

fun List<MusicCommunity>.toResponse(): MusicCommunitiesResponse {
    return MusicCommunitiesResponse(
        size = size,
        list = map { it.toResponse() }
    )
}

fun MusicVotePercentages.toResponse(): MusicVotePercentagesResponse {
    val list = map { vote -> vote.toResponse() }
    return MusicVotePercentagesResponse(list.size, list)
}

private fun MusicVotePercentage.toResponse() =
    MusicVotePercentageResponse(
        musicId = musicId.id,
        emojiId = emojiId.id,
        percentage = percentage
    )

fun MusicVotes.toResponse(): List<MusicVoteResponse> = map { vote ->
    MusicVoteResponse(
        musicId = vote.musicId.id,
        accountId = vote.accountId.id,
        emojiId = vote.emojiId.id
    )
}