package com.example.codehub.presentation.post.create

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codehub.R
import com.example.ds.designsystem.components.buttons.PrimaryButton
import com.example.ds.designsystem.components.buttons.SecondaryButton
import com.example.ds.designsystem.components.chips.SelectableChip
import com.example.ds.designsystem.components.fields.FormTextField
import com.example.ds.designsystem.components.fields.MultilineTextField
import com.example.codehub.presentation.post.create.CreatePostAction.ContentChanged
import com.example.codehub.presentation.post.create.CreatePostAction.TagToggled
import com.example.codehub.presentation.post.create.CreatePostAction.TitleChanged
import com.example.ds.designsystem.theme.DesignSystemTheme

@Composable
fun CreatePostScreen(
    state: CreatePostUiState,
    onAction: (CreatePostAction) -> Unit,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onContinueClick: (CreatePostUiState) -> Unit = {},
    onSaveDraftClick: () -> Unit = {}
) {
    val tags = listOf(
        stringResource(R.string.post_tag_kotlin),
        stringResource(R.string.post_tag_android),
        stringResource(R.string.post_tag_mvvm)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6FA))
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier
                .clickable(role = Role.Button, onClick = onBackClick)
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null,
                tint = Color(0xFFDF3211),
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = stringResource(R.string.post_back_to_feed),
                color = Color(0xFFDF3211),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = stringResource(R.string.post_creation_step),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFFEEE8), RoundedCornerShape(14.dp))
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    color = Color(0xFFDF3211),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    repeat(2) { index ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(4.dp)
                                .background(
                                    color = if (index == 0) Color(0xFFDF3211) else Color(0xFFE1E5ED),
                                    shape = RoundedCornerShape(50)
                                )
                        )
                    }
                }
            }

            Text(
                text = stringResource(R.string.post_creation_title),
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF252A38),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.post_creation_description),
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF697286)
            )
        }

        FormTextField(
            value = state.title,
            onValueChange = { onAction(TitleChanged(it)) },
            label = stringResource(R.string.post_title_label),
            placeholder = stringResource(R.string.post_title_hint)
        )

        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            MultilineTextField(
                value = state.content,
                onValueChange = { onAction(ContentChanged(it)) },
                label = stringResource(R.string.post_content_label),
                placeholder = stringResource(R.string.post_content_hint)
            )
            Text(
                text = stringResource(R.string.post_content_character_count, state.content.length),
                modifier = Modifier.align(Alignment.End),
                color = Color(0xFF737B8F),
                fontSize = 12.sp
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = stringResource(R.string.post_tags_label),
                color = Color(0xFF252A38),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = stringResource(R.string.post_tags_hint),
                color = Color(0xFF737B8F),
                style = MaterialTheme.typography.bodySmall
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                tags.forEach { tag ->
                    SelectableChip(
                        text = tag,
                        selected = tag in state.selectedTags,
                        onClick = { onAction(TagToggled(tag)) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(2.dp))
        PrimaryButton(
            text = stringResource(R.string.post_continue),
            onClick = { onContinueClick(state) },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            enabled = state.canContinue
        )
        SecondaryButton(
            text = stringResource(R.string.post_save_draft),
            onClick = onSaveDraftClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F6FA, widthDp = 390)
@Composable
private fun CreatePostScreenPreview() {
    DesignSystemTheme {
        CreatePostScreen(
            state = CreatePostUiState(),
            onAction = {}
        )
    }
}