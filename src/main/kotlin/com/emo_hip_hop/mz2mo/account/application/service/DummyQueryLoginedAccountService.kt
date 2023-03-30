package com.emo_hip_hop.mz2mo.account.application.service

import com.emo_hip_hop.mz2mo.account.application.port.input.QueryLoginedAccountUseCase
import com.emo_hip_hop.mz2mo.account.domain.Account
import com.emo_hip_hop.mz2mo.account.domain.AccountId
import com.emo_hip_hop.mz2mo.global.UseCase
import com.emo_hip_hop.mz2mo.global.validate.domain.UUID_EXAMPLE

@UseCase
class DummyQueryLoginedAccountService : QueryLoginedAccountUseCase {
    override fun invoke(): Account {
        return Account(AccountId(UUID_EXAMPLE))
    }
}