package com.emo_hip_hop.mz2mo.music.domain

class MusicCommunityPartialNotFoundException(key: String, value: String) : RuntimeException("music community partial not found! - $key: '$value'")