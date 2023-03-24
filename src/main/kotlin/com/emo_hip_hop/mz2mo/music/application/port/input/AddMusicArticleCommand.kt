package com.emo_hip_hop.mz2mo.music.application.port.input

import com.emo_hip_hop.mz2mo.global.validate.SelfValidating
import javax.validation.constraints.NotBlank

class AddMusicArticleCommand(
    @field:NotBlank
    val youtubeId: String
): SelfValidating<AddMusicArticleCommand>()