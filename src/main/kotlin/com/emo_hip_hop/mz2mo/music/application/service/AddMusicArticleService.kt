package com.emo_hip_hop.mz2mo.music.application.service

import com.emo_hip_hop.mz2mo.music.application.port.input.AddMusicArticleUseCase
import com.emo_hip_hop.mz2mo.music.application.port.input.AddMusicArticleCommand
import com.emo_hip_hop.mz2mo.music.application.port.output.CreateMusicArticlePort
import com.emo_hip_hop.mz2mo.music.application.port.output.ExistsMusicPort
import com.emo_hip_hop.mz2mo.music.domain.MusicArticle
import com.emo_hip_hop.mz2mo.global.UseCase
import com.emo_hip_hop.mz2mo.music.application.port.output.CreateMusicPort
import com.emo_hip_hop.mz2mo.music.domain.Music
import com.emo_hip_hop.mz2mo.music.domain.MusicVotes

@UseCase
class AddMusicArticleService(
    private val createMusicArticlePort: CreateMusicArticlePort,
    private val existsMusicPort: ExistsMusicPort,
    private val createMusicPort: CreateMusicPort,
): AddMusicArticleUseCase {
    override fun invoke(command: AddMusicArticleCommand): MusicArticle {
        checkAlreadyExists(command)
        val music = Music.withoutId(command.youtubeId)
        val savedMusic = createMusicPort.create(music)
        val musicArticle = MusicArticle.withoutId(savedMusic, MusicVotes.empty())
        return createMusicArticlePort.create(musicArticle)
    }

    private fun checkAlreadyExists(command: AddMusicArticleCommand) {
        val isExists = existsMusicPort.byYoutubeId(command.youtubeId)
        if (isExists) throw MusicAlreadyExistsException(command.youtubeId)
    }
}