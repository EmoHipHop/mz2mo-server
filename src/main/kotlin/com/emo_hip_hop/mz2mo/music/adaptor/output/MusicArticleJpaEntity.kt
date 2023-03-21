package com.emo_hip_hop.mz2mo.music.adaptor.output

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "music_articles")
class MusicArticleJpaEntity(
    @Id
    val id: ObjectId?,
    val youtubeTrackId: String
)