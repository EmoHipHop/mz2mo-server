package com.emo_hip_hop.mz2mo.music.adapter.output.persistence.entity

import javax.persistence.*

@Entity
@Table(name = "musics", indexes = [Index(name = "idx_id", columnList = "id")])
class MusicJpaEntity(
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    var id: String?,
    val youtubeId: String
)