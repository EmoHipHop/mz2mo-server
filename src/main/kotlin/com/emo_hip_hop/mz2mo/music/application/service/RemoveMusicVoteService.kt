package com.emo_hip_hop.mz2mo.music.application.service

import com.emo_hip_hop.mz2mo.global.UseCase
import com.emo_hip_hop.mz2mo.music.application.port.input.RemoveMusicVoteCommand
import com.emo_hip_hop.mz2mo.music.application.port.input.RemoveMusicVoteUseCase
import com.emo_hip_hop.mz2mo.music.application.port.output.QueryMusicCommunityPort
import com.emo_hip_hop.mz2mo.music.application.port.output.UpdateMusicCommunityPort
import com.emo_hip_hop.mz2mo.music.domain.MusicCommunity
import com.emo_hip_hop.mz2mo.music.domain.exception.MusicCommunityOrPartialNotFoundException
import com.emo_hip_hop.mz2mo.music.domain.exception.MusicVoteNotFoundException

@UseCase
class RemoveMusicVoteService(
    private val queryMusicCommunityPort: QueryMusicCommunityPort,
    private val updateMusicCommunityPort: UpdateMusicCommunityPort
) : RemoveMusicVoteUseCase {
    override fun invoke(command: RemoveMusicVoteCommand): MusicCommunity {
        val musicId = command.musicId
        val musicCommunity = queryMusicCommunityPort.findByMusicId(musicId)
            ?: throw MusicCommunityOrPartialNotFoundException("musicId", musicId.id)

        checkCanRemoveVote(musicCommunity, command)

        val voteToRemove = musicCommunity.votes.first {
            it.accountId.id == command.accountId.id &&
                it.emojiId.id == command.emojiId.id
        }
        val musicCommunityToUpdate = musicCommunity.removeVote(voteToRemove)
        return updateMusicCommunityPort.update(musicCommunityToUpdate)
    }

    private fun checkCanRemoveVote(musicCommunity: MusicCommunity, command: RemoveMusicVoteCommand) {
        val musicId = command.musicId

        val voteExists = musicCommunity.votes.any {
            it.accountId.id == command.accountId.id &&
                it.emojiId.id == command.emojiId.id
        }
        if (!voteExists) throw MusicVoteNotFoundException(musicId, command.accountId, command.emojiId)
    }
}