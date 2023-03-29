package com.emo_hip_hop.mz2mo.music.application.service

import com.emo_hip_hop.mz2mo.global.UseCase
import com.emo_hip_hop.mz2mo.music.application.port.input.SearchMusicCommunityQuery
import com.emo_hip_hop.mz2mo.music.application.port.input.SearchMusicCommunityUseCase
import com.emo_hip_hop.mz2mo.music.application.port.output.SearchMusicCommunityPort
import com.emo_hip_hop.mz2mo.music.domain.MusicCommunity

@UseCase
class SearchMusicCommunityService(
    private val searchMusicCommunityPort: SearchMusicCommunityPort
) : SearchMusicCommunityUseCase {
    override fun invoke(query: SearchMusicCommunityQuery): List<MusicCommunity> {
        return searchMusicCommunityPort.search(query.pageable)
    }
}