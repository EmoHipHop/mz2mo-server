package com.emo_hip_hop.mz2mo.music.domain

class MusicAlreadyExistsException(val youtubeId: String) : RuntimeException("music that youtubeId is '$youtubeId' is already exists!") {

}
