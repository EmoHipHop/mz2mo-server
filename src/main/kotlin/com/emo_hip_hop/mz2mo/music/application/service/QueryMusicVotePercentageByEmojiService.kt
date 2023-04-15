package com.emo_hip_hop.mz2mo.music.application.service

import com.emo_hip_hop.mz2mo.music.application.port.input.QueryMusicVotePercentageByEmojiUseCase
import com.emo_hip_hop.mz2mo.music.application.port.output.QueryMusicVotePort
import com.emo_hip_hop.mz2mo.music.domain.MusicId
import com.emo_hip_hop.mz2mo.music.domain.MusicVotePercentage
import com.emo_hip_hop.mz2mo.music.domain.MusicVotePercentages
import com.emo_hip_hop.mz2mo.music.domain.MusicVotes
import org.springframework.stereotype.Service

@Service
class QueryMusicVotePercentageByEmojiService(
    private val queryMusicVotesPort: QueryMusicVotePort,
) : QueryMusicVotePercentageByEmojiUseCase {
    override fun invoke(musicId: MusicId): MusicVotePercentages {
        val votes = queryMusicVotesPort(musicId)
        return aggregateVoteAndCalculatePercentageByEmoji(musicId, votes)
    }

    private fun aggregateVoteAndCalculatePercentageByEmoji(musicId: MusicId, votes: MusicVotes): MusicVotePercentages {
        val totalVotes = votes.size
        val aggregatedVotesByEmoji = votes.groupBy { it.emojiId }
        val calculatedPercentageList = aggregatedVotesByEmoji.map { (emojiId, votes) ->
            val percentage = votes.size.toDouble() / totalVotes * 100
            return@map MusicVotePercentage(musicId, emojiId, percentage)
        }
        return MusicVotePercentages.of(calculatedPercentageList)
    }
}