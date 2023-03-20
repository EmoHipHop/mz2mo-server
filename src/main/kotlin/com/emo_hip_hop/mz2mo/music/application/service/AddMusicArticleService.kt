package com.emo_hip_hop.mz2mo.music.application.service

import com.emo_hip_hop.mz2mo.music.application.port.input.AddMusicArticleUseCase
import com.emo_hip_hop.mz2mo.music.application.port.input.AddMusicCommand
import com.emo_hip_hop.mz2mo.music.application.port.output.CreateMusicArticlePort
import com.emo_hip_hop.mz2mo.music.application.port.output.ExistsMusicArticlePort
import com.emo_hip_hop.mz2mo.music.domain.MusicArticle
import com.emo_hip_hop.mz2mo.music.domain.MusicVotes
import com.emo_hip_hop.mz2mo.global.UseCase

@UseCase
class AddMusicArticleService(
    private val createMusicArticlePort: CreateMusicArticlePort,
    private val existsMusicArticlePort: ExistsMusicArticlePort
): AddMusicArticleUseCase {
    override fun invoke(command: AddMusicCommand): MusicArticle {
        checkAlreadyExists(command)
        val musicArticle = MusicArticle.withoutId(command.youtubeTrackId, MusicVotes.empty())
        return createMusicArticlePort.create(musicArticle)
    }

    private fun checkAlreadyExists(command: AddMusicCommand) {
        val isExists = existsMusicArticlePort.byId(command.youtubeTrackId)
        if (isExists) throw MusicArticleAlreadyExistsException(command.youtubeTrackId)
    }
}