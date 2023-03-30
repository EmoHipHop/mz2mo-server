package com.emo_hip_hop.mz2mo.global.error.domain

enum class ErrorCode(
    val domain: DomainCode,
    val cause: CauseOriginCode,
    val index: Int
) {
    ALREADY_VOTE(DomainCode.MUSIC_VOTE, CauseOriginCode.USER, 1),
    EXCEED_MAXIMUM_VOTE_PER_USER(DomainCode.MUSIC_VOTE, CauseOriginCode.USER, 2),
    EMPTY_MUSIC_ID(DomainCode.MUSIC_VOTE, CauseOriginCode.CLIENT, 3),
    MUSIC_OUT_OF_SYNC(DomainCode.MUSIC, CauseOriginCode.SERVER, 4),
    MUSIC_COMMUNITY_OR_PARTIAL_NOT_FOUND(DomainCode.MUSIC, CauseOriginCode.SERVER, 5),
    MUSIC_VOTE_NOT_FOUND(DomainCode.MUSIC_VOTE, CauseOriginCode.CLIENT, 6),
    MUSIC_NOT_FOUND(DomainCode.MUSIC, CauseOriginCode.CLIENT, 7),
    MUSIC_ALREADY_EXISTS(DomainCode.MUSIC, CauseOriginCode.CLIENT, 8);

    val code = String.format("%02d%01d%03d", domain.code, cause.code, index)
}