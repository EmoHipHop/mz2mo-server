package com.emo_hip_hop.mz2mo.global.error.adapter.input.web

import com.emo_hip_hop.mz2mo.global.error.domain.ErrorCode
import com.fasterxml.jackson.databind.annotation.JsonSerialize

data class ErrorResponse(
    val code: Int,
    @JsonSerialize(using = ErrorCodeSerializer::class)
    val codeDetails: ErrorCode,
    val message: String
)