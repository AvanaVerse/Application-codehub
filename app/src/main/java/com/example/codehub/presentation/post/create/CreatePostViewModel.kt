package com.example.codehub.presentation.post.create

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreatePostViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CreatePostUiState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: CreatePostAction) {
        _uiState.update { state ->
            when (action) {
                is CreatePostAction.TitleChanged -> state.copy(title = action.value)
                is CreatePostAction.ContentChanged -> state.copy(content = action.value)
                is CreatePostAction.TagToggled -> state.copy(
                    selectedTags = if (action.tag in state.selectedTags) {
                        state.selectedTags - action.tag
                    } else {
                        state.selectedTags + action.tag
                    }
                )
            }
        }
    }
}