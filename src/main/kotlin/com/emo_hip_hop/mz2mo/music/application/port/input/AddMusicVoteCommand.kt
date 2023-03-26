package com.emo_hip_hop.mz2mo.music.application.port.input

import com.emo_hip_hop.mz2mo.global.validate.UUID_PATTERN
import com.emo_hip_hop.mz2mo.global.validate.SelfValidating
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

class AddMusicVoteCommand(
    @field:NotBlank
    @Pattern(regexp = UUID_PATTERN, message = "음악 ID 형식이 올바르지 않습니다.")
    val musicId: String,
    @field:NotBlank
    @Pattern(regexp = UUID_PATTERN, message = "계정 ID 형식이 올바르지 않습니다.")
    val accountId: String,
    @field:NotBlank
    @Pattern(regexp = UUID_PATTERN, message = "이모지 ID 형식이 올바르지 않습니다.")
    val emojiId: String
): SelfValidating<AddMusicVoteCommand>()
