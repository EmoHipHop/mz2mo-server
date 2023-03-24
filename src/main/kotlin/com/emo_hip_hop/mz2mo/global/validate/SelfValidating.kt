package com.emo_hip_hop.mz2mo.global.validate

import javax.validation.*


abstract class SelfValidating<T> {
    private val validator: Validator

    init {
        val factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
        validator = factory.validator
    }

    protected fun validateSelf() {
        val violations: Set<ConstraintViolation<T>> = validator.validate(this as T)
        if (violations.isNotEmpty())
            throw ConstraintViolationException(violations)
    }
}