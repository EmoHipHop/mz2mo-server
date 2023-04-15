package com.emo_hip_hop.mz2mo.global.error.adapter.input.web

import com.emo_hip_hop.mz2mo.global.error.domain.ERROR_CODE_EXAMPLE
import com.emo_hip_hop.mz2mo.global.error.domain.ERROR_CODE_PATTERN
import com.emo_hip_hop.mz2mo.global.error.domain.ERROR_MESSAGE_EXAMPLE
import com.emo_hip_hop.mz2mo.global.error.domain.ErrorCode
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.Pattern

@Schema(name = "ErrorResponse", description = "오류 발생시 오류 정보를 명시하는 응답입니다.")
data class ErrorResponse(
    @Schema(description = "오류 코드", example = ERROR_CODE_EXAMPLE)
    @field:Pattern(regexp = ERROR_CODE_PATTERN)
    val code: String,
    @Schema(description = "오류 코드 상세", example = ERROR_CODE_EXAMPLE)
    @JsonSerialize(using = ErrorCodeSerializer::class)
    val codeDetails: ErrorCode,
    @Schema(description = "오류 메세지", example = ERROR_MESSAGE_EXAMPLE)
    val message: String
) {
    companion object {
        fun of(code: ErrorCode, message: String?) = ErrorResponse(code.code, code, message.orEmpty())
    }
}