package com.example.watchlist.domain

import com.example.watchlist.data.model.SubmitState
import com.example.watchlist.data.response.SubmitUserEmailResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SubmitUserEmailUseCase @Inject constructor() {

    operator fun invoke(email: String): Flow<SubmitState<SubmitUserEmailResponse>> {
        return flow {
            emit(SubmitState.InProgress)
            delay(5000)
            emit(
                SubmitState.Success(
                    SubmitUserEmailResponse(
                        email = email,
                        hasAccount = false,
                        name = "Test User",
                    ),
                ),
            )
        }
    }
}
