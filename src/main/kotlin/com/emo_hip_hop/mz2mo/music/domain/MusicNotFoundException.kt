package com.emo_hip_hop.mz2mo.music.domain

class MusicNotFoundException(key: String, value: String): RuntimeException("music not found! - $key: '$value'")