package com.emo_hip_hop.mz2mo.music.domain

class MusicOutOfSyncException(key: String, value: String, syncTo: String) : RuntimeException("music out of sync! - $key: '$value', out of sink to: '$syncTo'")