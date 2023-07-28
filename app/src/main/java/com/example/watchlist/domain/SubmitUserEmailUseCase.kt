package com.example.watchlist.domain

import com.example.watchlist.data.model.SubmitState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SubmitUserEmailUseCase @Inject constructor() {

    operator fun invoke(email: String): Flow<SubmitState<Boolean>> {
        return flow {
            emit(SubmitState.InProgress)
            delay(5000)
            emit(SubmitState.Success(email.isNotEmpty()))
        }
    }
}
