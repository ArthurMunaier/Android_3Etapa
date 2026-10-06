package com.example.diariofilmesroomapi

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FilmeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FilmeRepository(application)

    val filmes: StateFlow<List<Filme>> =
        repository.observarFilmes()
            .let { fluxo ->
                kotlinx.coroutines.flow.stateIn(
                    viewModelScope,
                    kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000),
                    emptyList()
                )
            }

    private val _estado = MutableStateFlow<SyncState>(SyncState.Idle)
    val estado: StateFlow<SyncState> = _estado.asStateFlow()

    fun sincronizar() {
        viewModelScope.launch {
            _estado.value = SyncState.Loading

            try {
                repository.sincronizar()
                _estado.value = SyncState.Idle
            } catch (e: Exception) {
                _estado.value = SyncState.Error(
                    e.message ?: "Não foi possível buscar os filmes."
                )
            }
        }
    }
}
