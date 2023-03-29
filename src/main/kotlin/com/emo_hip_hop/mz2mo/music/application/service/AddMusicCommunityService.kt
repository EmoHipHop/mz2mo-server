package com.emo_hip_hop.mz2mo.music.application.service

import com.emo_hip_hop.mz2mo.music.application.port.input.AddMusicCommunityUseCase
import com.emo_hip_hop.mz2mo.music.application.port.input.AddMusicCommunityCommand
import com.emo_hip_hop.mz2mo.music.application.port.output.CreateMusicCommunityPort
import com.emo_hip_hop.mz2mo.music.application.port.output.ExistsMusicPort
import com.emo_hip_hop.mz2mo.music.domain.MusicCommunity
import com.emo_hip_hop.mz2mo.global.UseCase
import com.emo_hip_hop.mz2mo.music.domain.exception.MusicAlreadyExistsException
import com.emo_hip_hop.mz2mo.music.application.port.output.CreateMusicPort
import com.emo_hip_hop.mz2mo.music.domain.Music
import com.emo_hip_hop.mz2mo.music.domain.MusicVotes

@UseCase
class AddMusicCommunityService(
    private val createMusicCommunityPort: CreateMusicCommunityPort,
    private val existsMusicPort: ExistsMusicPort,
    private val createMusicPort: CreateMusicPort,
) : AddMusicCommunityUseCase {
    override fun invoke(command: AddMusicCommunityCommand): MusicCommunity {
        checkAlreadyExists(command)
        val music = Music.withoutId(command.youtubeId)
        val savedMusic = createMusicPort.create(music)
        val musicCommunity = MusicCommunity.withoutId(savedMusic, MusicVotes.empty())
        return createMusicCommunityPort.create(musicCommunity)
    }

    private fun checkAlreadyExists(command: AddMusicCommunityCommand) {
        val isExists = existsMusicPort.byYoutubeId(command.youtubeId)
        if (isExists) throw MusicAlreadyExistsException("youtubeId", command.youtubeId)
    }
}