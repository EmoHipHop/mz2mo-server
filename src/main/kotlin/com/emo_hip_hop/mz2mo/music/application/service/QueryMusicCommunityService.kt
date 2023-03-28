package com.emo_hip_hop.mz2mo.music.application.service

import com.emo_hip_hop.mz2mo.global.UseCase
import com.emo_hip_hop.mz2mo.music.application.port.input.QueryMusicCommunityUseCase
import com.emo_hip_hop.mz2mo.music.application.port.output.QueryMusicCommunityPort
import com.emo_hip_hop.mz2mo.music.domain.MusicCommunity
import com.emo_hip_hop.mz2mo.music.domain.MusicCommunityOrPartialNotFoundException
import com.emo_hip_hop.mz2mo.music.domain.MusicId

@UseCase
class QueryMusicCommunityService(
    private val queryMusicCommunityPort: QueryMusicCommunityPort
): QueryMusicCommunityUseCase {
    override fun invoke(musicId: MusicId): MusicCommunity {
        val communityOrNull = queryMusicCommunityPort.findByMusicId(musicId)
        return communityOrNull ?: throw MusicCommunityOrPartialNotFoundException("musicId", musicId.id)
    }
}