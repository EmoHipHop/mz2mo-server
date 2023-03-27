package com.emo_hip_hop.mz2mo

import com.emo_hip_hop.mz2mo.music.adapter.output.persistence.CustomMusicCommunityRepositoryImpl
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = [
    CustomMusicCommunityRepositoryImpl::class
])
class Mz2moServerApplication

fun main(args: Array<String>) {
    runApplication<Mz2moServerApplication>(*args)
}
