package com.emo_hip_hop.mz2mo.music.adapter.output.persistence.repository

import com.emo_hip_hop.mz2mo.global.common.domain.Pageable
import com.emo_hip_hop.mz2mo.music.adapter.output.persistence.entity.MusicCommunitiesJpaEntity
import com.linecorp.kotlinjdsl.querydsl.expression.column
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.listQuery
import org.springframework.stereotype.Component

@Component
class CustomMusicCommunityRepositoryImpl(
    private val queryFactory: SpringDataQueryFactory
) : CustomMusicCommunityRepository {
    override fun search(pageable: Pageable): List<MusicCommunitiesJpaEntity> {
        return queryFactory.listQuery {
            select(entity(MusicCommunitiesJpaEntity::class))
            from(entity(MusicCommunitiesJpaEntity::class))
            where(column(MusicCommunitiesJpaEntity::musicId).greaterThan(pageable.lastIndexId))
            orderBy(column(MusicCommunitiesJpaEntity::musicId).asc())
            limit(pageable.size)
        }
    }
}