package com.emo_hip_hop.mz2mo.music.adaptor.output.persistence

import org.springframework.data.mongodb.repository.MongoRepository

interface SpringDataMusicArticleRepository: MongoRepository<MusicArticleJpaEntity, String> {

}
