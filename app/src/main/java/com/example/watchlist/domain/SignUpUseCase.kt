package com.example.watchlist.domain

import com.example.watchlist.data.model.SubmitState
import com.example.watchlist.data.request.SignUpRequest
import com.example.watchlist.data.response.SignUpResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor() {

    operator fun invoke(request: SignUpRequest): Flow<SubmitState<SignUpResponse>> {
        return flow {
            emit(SubmitState.InProgress)
            delay(5000)
            emit(
                SubmitState.Success(
                    SignUpResponse(true),
                ),
            )
        }
    }
}
