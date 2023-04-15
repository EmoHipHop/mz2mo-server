package com.emo_hip_hop.mz2mo.global.error.domain

/*
⚠️ Cause origin code는 에러가 발생한 근본적인 원인에 기반합니다.
- 근본적인 원인은 비즈니스로직과 대응되는 해당 오류가 발생한 원인입니다.
 */
enum class CauseOriginCode(val code: Int) {
    SERVER(1),
    CLIENT(2),
    USER(3),
}