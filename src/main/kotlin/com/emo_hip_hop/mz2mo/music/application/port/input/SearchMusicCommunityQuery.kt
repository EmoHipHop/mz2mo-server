package com.emo_hip_hop.mz2mo.music.application.port.input

import com.emo_hip_hop.mz2mo.global.common.domain.Pageable
import com.emo_hip_hop.mz2mo.global.validate.domain.SelfValidating

/*
나중에 search, emoji, isTrending isRecommended 등의 필터를 추가할 수 있을 것 같다.
 */
class SearchMusicCommunityQuery(
    val pageable: Pageable
) : SelfValidating<SearchMusicCommunityQuery>()