package com.emo_hip_hop.mz2mo.music.adaptor.output.persistence

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "music_votes")
class MusicVoteJpaEntity(
    @Id
    val id: ObjectId,
    val musicArticleId: String,
    val accountId: String,
    val emojiId: String
)