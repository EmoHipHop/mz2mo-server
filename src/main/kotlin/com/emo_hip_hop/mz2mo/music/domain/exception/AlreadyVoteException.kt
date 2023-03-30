package com.emo_hip_hop.mz2mo.music.domain.exception

class AlreadyVoteException(id: String, accountId: String, emojiId: String) : RuntimeException("music and emoji already voted! - id: '$id', accountId: '$accountId', emojiId: '$emojiId")