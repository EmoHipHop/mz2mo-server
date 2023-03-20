package com.emo_hip_hop.mz2mo.domain.music.application.port.input

import com.emo_hip_hop.mz2mo.global.validate.SelfValidating
import javax.validation.constraints.NotBlank

class AddMusicCommand(
    @field:NotBlank
    val youtubeTrackId: String
): SelfValidating<AddMusicCommand>()