package com.emo_hip_hop.mz2mo.global.common.domain

class Pageable private constructor (
    val lastIndexId: String,
    val size: Int
) {
    companion object {
        private const val DEFAULT_SIZE = 10
        private const val DEFAULT_LAST_INDEX_ID: String = ""
        fun of(lastIndexId: String, size: Int): Pageable {
            if (size < 0) throw IllegalArgumentException("size must be greater than 0")
            return Pageable(lastIndexId, size)
        }
        fun of(lastIndexId: String): Pageable = of(lastIndexId, DEFAULT_SIZE)
        fun of(): Pageable = Pageable(DEFAULT_LAST_INDEX_ID, DEFAULT_SIZE)
    }
}