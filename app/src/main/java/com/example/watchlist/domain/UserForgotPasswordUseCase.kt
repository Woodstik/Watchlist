package com.example.watchlist.domain

import com.example.watchlist.data.model.SubmitState
import com.example.watchlist.data.request.ForgotPasswordRequest
import com.example.watchlist.data.response.ForgotPasswordResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserForgotPasswordUseCase @Inject constructor() {
    operator fun invoke(request: ForgotPasswordRequest): Flow<SubmitState<ForgotPasswordResponse>> {
        return flow {
            emit(SubmitState.InProgress)
            delay(5000)
            emit(
                SubmitState.Success(
                    ForgotPasswordResponse(request.email.isNotEmpty()),
                ),
            )
        }
    }
}
