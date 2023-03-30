package com.emo_hip_hop.mz2mo.music.application.port.input

import com.emo_hip_hop.mz2mo.global.validate.domain.SelfValidating
import javax.validation.constraints.NotBlank

class AddMusicCommunityCommand(
    @field:NotBlank
    val youtubeId: String
) : SelfValidating<AddMusicCommunityCommand>()