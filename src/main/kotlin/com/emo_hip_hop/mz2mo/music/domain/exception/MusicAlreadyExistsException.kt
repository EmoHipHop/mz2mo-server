package com.emo_hip_hop.mz2mo.music.domain.exception

class MusicAlreadyExistsException(key: String, value: String) : RuntimeException("music already exists! - $key: '$value'")