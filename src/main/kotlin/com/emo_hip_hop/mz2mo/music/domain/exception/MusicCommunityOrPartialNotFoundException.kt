package com.emo_hip_hop.mz2mo.music.domain.exception

class MusicCommunityOrPartialNotFoundException(key: String, value: String) : RuntimeException("music community (or partial) not found! - $key: '$value'")