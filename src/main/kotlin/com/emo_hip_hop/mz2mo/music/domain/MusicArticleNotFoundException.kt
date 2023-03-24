package com.emo_hip_hop.mz2mo.music.domain

class MusicArticleNotFoundException(key: String, value: String) : RuntimeException("music article not found! - $key: '$value'")