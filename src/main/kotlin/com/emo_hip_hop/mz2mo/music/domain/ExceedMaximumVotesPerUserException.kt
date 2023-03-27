package com.emo_hip_hop.mz2mo.music.domain

class ExceedMaximumVotesPerUserException(id: String, currentVoteCount: Int, maxVoteCount: Int): RuntimeException("Exceeded maximum number of vote per user! - id: '$id', currentVoteCount: '$currentVoteCount', maxVoteCount: '$maxVoteCount'")