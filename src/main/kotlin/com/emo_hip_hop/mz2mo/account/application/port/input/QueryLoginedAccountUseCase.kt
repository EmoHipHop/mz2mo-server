package com.emo_hip_hop.mz2mo.account.application.port.input

import com.emo_hip_hop.mz2mo.account.domain.Account

interface QueryLoginedAccountUseCase {
    operator fun invoke(): Account
}