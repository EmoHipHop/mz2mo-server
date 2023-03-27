package com.emo_hip_hop.mz2mo.music.adapter.output.persistence

import javax.persistence.*

@Entity
@Table(name = "music_votes", indexes = [Index(name = "idx_id", columnList = "id")])
class MusicVoteJpaEntity(
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    var id: String?,
    val musicId: String,
    val accountId: String,
    val emojiId: String
)