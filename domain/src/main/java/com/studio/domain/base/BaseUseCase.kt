package com.studio.domain.base

abstract class BaseUseCase<INPUT, OUTPUT> {
    protected abstract suspend fun create(input: INPUT): OUTPUT

    suspend fun execute(input: INPUT?): Result<OUTPUT> {
        return try {
            Result.success(create(input!!))
        } catch (throwable: Throwable) {
            Result.failure(throwable)
        }
    }
}
