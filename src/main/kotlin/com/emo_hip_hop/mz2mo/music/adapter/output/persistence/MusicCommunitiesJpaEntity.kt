package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import javax.persistence.*

@Entity
@Table(name = "music_communities", indexes = [Index(name = "idx_id", columnList = "id")])
class MusicCommunitiesJpaEntity(
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    var id: String?,
    val musicId: String
)