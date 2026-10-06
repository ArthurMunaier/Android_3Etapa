package com.example.diariofilmesroomapi

sealed interface SyncState {
    data object Idle : SyncState
    data object Loading : SyncState
    data class Error(val mensagem: String) : SyncState
}
