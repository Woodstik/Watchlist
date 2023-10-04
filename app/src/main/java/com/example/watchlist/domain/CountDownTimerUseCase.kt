package com.example.watchlist.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CountDownTimerUseCase @Inject constructor() {
    operator fun invoke(timeInSeconds: Int): Flow<Int> = flow {
        var count = 0
        emit(timeInSeconds)
        while (count < timeInSeconds) {
            delay(1000)
            count += 1
            emit(timeInSeconds - count)
        }
    }
}
