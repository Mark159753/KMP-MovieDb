package org.moviedb.kmp.domain.actions

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import org.moviedb.kmp.common.ApiResponse
import org.moviedb.kmp.network.models.error.ErrorResponse

interface UIActions {

    val actions: Flow<Actions>

    suspend fun sendAction(action: Actions)

    suspend fun handleApiError(error: ApiResponse<Nothing, ErrorResponse>)
}

class UIActionImp:UIActions{

    private val _actions = Channel<Actions>()
    override val actions: Flow<Actions>
        get() = _actions.receiveAsFlow()

    override suspend fun sendAction(action: Actions) {
        _actions.send(action)
    }

    override suspend fun handleApiError(error: ApiResponse<Nothing, ErrorResponse>) {
        when(error){
            is ApiResponse.Error.HttpError -> {
                val msg = error.errorBody?.statusMessage
                sendAction(
                    Actions.Error(
                        DisplayError.Message(msg = msg ?: "")
                    )
                )
            }
            ApiResponse.Error.NetworkError -> {
                sendAction(
                    Actions.Error(
                        DisplayError.Connectivity
                    )
                )
            }
            ApiResponse.Error.SerializationError -> {
                sendAction(
                    Actions.Error(
                        DisplayError.ApiParse
                    )
                )
            }
            else -> {}
        }
    }
}
