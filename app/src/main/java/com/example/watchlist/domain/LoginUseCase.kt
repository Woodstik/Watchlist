package com.example.watchlist.domain

import com.example.watchlist.data.model.SubmitState
import com.example.watchlist.data.request.LoginRequest
import com.example.watchlist.data.response.LoginResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCase @Inject constructor() {

    operator fun invoke(request: LoginRequest): Flow<SubmitState<LoginResponse>> {
        return flow {
            emit(SubmitState.InProgress)
            delay(5000)
            emit(
                SubmitState.Success(
                    LoginResponse(""),
                ),
            )
        }
    }
}
