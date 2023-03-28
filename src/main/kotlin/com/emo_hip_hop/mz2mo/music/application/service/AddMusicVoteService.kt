package com.emo_hip_hop.mz2mo.music.application.service

import com.emo_hip_hop.mz2mo.global.UseCase
import com.emo_hip_hop.mz2mo.music.application.port.input.AddMusicVoteCommand
import com.emo_hip_hop.mz2mo.music.application.port.input.AddMusicVoteUseCase
import com.emo_hip_hop.mz2mo.music.application.port.output.QueryMusicCommunityPort
import com.emo_hip_hop.mz2mo.music.application.port.output.UpdateMusicCommunityPort
import com.emo_hip_hop.mz2mo.music.domain.*
import org.springframework.beans.factory.annotation.Value

@UseCase
class AddMusicVoteService(
    private val queryMusicCommunityPort: QueryMusicCommunityPort,
    @Value("\${mz2mo.music.vote.max-vote-count}")
    private val maxVoteCount: Int,
    private val updateMusicCommunityPort: UpdateMusicCommunityPort
): AddMusicVoteUseCase {
    override fun invoke(command: AddMusicVoteCommand): MusicCommunity {
        val musicId = command.musicId
        val musicCommunity = queryMusicCommunityPort.findByMusicId(musicId)
                ?: throw MusicCommunityOrPartialNotFoundException("musicId", musicId.id)

        checkCanVote(musicCommunity, command)

        val vote = MusicVote(null, musicId, command.accountId, command.emojiId)
        val musicCommunityToUpdate = musicCommunity.addVote(vote)
        return updateMusicCommunityPort.update(musicCommunityToUpdate)
    }

    private fun checkCanVote(musicCommunity: MusicCommunity, command: AddMusicVoteCommand) {
        val musicId = command.musicId

        val currentVoteCount = musicCommunity.votes.size
        if(currentVoteCount >= maxVoteCount) throw ExceedMaximumVotesPerUserException(musicId.id, currentVoteCount, maxVoteCount)

        val isAlreadyVote = musicCommunity.votes.any { it.accountId.id == command.accountId.id && it.emojiId.id == command.emojiId.id }
        if(isAlreadyVote) throw AlreadyVoteException(musicId.id, command.accountId.id, command.emojiId.id)
    }
}