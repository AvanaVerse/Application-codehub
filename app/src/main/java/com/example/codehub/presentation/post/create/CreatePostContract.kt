package com.example.codehub.presentation.post.create

data class CreatePostUiState(
    val title: String = "",
    val content: String = "",
    val selectedTags: Set<String> = emptySet()
) {

    val canContinue: Boolean
        get() = title.isNotBlank() && content.isNotBlank()
}

sealed interface CreatePostAction {
    data class TitleChanged(val value: String) : CreatePostAction
    data class ContentChanged(val value: String) : CreatePostAction
    data class TagToggled(val tag: String) : CreatePostAction
}