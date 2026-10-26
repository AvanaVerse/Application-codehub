package com.example.codehub.presentation.post.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codehub.R
import com.example.ds.designsystem.components.BottomNavigation
import com.example.ds.designsystem.components.BottomNavigationItem
import com.example.ds.designsystem.components.PostCard
import com.example.ds.designsystem.components.SearchBar
import com.example.ds.designsystem.components.TopBar
import com.example.ds.designsystem.components.buttons.BackButton
import com.example.ds.designsystem.components.buttons.PrimaryButton
import com.example.ds.designsystem.theme.DesignSystemTheme

@Composable
fun PostDetailsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onSubmitResponse: (String) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    var responseText by remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFF5F6FA),
        topBar = {
            Column {
                TopBar(
                    appName = stringResource(R.string.app_name),
                    modifier = Modifier.statusBarsPadding()
                )
                SearchBar(
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    placeholder = stringResource(R.string.search_placeholder)
                )
            }
        },
        bottomBar = {
            BottomNavigation(
                items = listOf(
                    BottomNavigationItem(stringResource(R.string.feed), Icons.Outlined.Home),
                    BottomNavigationItem(stringResource(R.string.communities), Icons.Outlined.Groups),
                    BottomNavigationItem(stringResource(R.string.chat_ai), Icons.Outlined.AutoAwesome),
                    BottomNavigationItem(stringResource(R.string.analyze_code), Icons.Outlined.Code)
                ),
                selectedIndex = 0,
                onItemSelected = {}
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 14.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BackButton(
                onClick = onBackClick,
                text = stringResource(R.string.post_details_back),
                modifier = Modifier.height(32.dp)
            )

            Text(
                text = stringResource(R.string.post_details_title),
                modifier = Modifier.padding(bottom = 2.dp),
                color = Color(0xFF252A38),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            PublishedBanner()

            PostCard(
                authorName = stringResource(R.string.user_name),
                metadata = stringResource(R.string.post_details_meta),
                title = stringResource(R.string.publication_preview_title),
                bodyParagraphs = listOf(
                    stringResource(R.string.publication_preview_body_first),
                    stringResource(R.string.publication_preview_body_second)
                ),
                tags = listOf(
                    stringResource(R.string.post_tag_kotlin),
                    stringResource(R.string.post_tag_stateflow)
                ),
                likesLabel = stringResource(R.string.post_likes_count),
                responsesLabel = stringResource(R.string.post_responses_count)
            )

            ResponseComposer(
                value = responseText,
                onValueChange = { responseText = it },
                onSubmit = { onSubmitResponse(responseText.trim()) }
            )
        }
    }
}

@Composable
private fun PublishedBanner() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Color(0xFFEDF7F2)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.CheckCircle,
                contentDescription = null,
                tint = Color(0xFF288262),
                modifier = Modifier.size(20.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = stringResource(R.string.post_published_title),
                    color = Color(0xFF288262),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = stringResource(R.string.post_published_message),
                    color = Color(0xFF697286),
                    fontSize = 10.sp
                )
                
            }
        }
    }
}

@Composable
private fun ResponseComposer(
    value: String,
    onValueChange: (String) -> Unit,
    onSubmit: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE1E5ED))
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = stringResource(R.string.post_first_response_title),
                color = Color(0xFF252A38),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 42.dp)
                    .background(Color(0xFFF5F6FA), RoundedCornerShape(9.dp))
                    .padding(horizontal = 10.dp, vertical = 12.dp),
                singleLine = true,
                cursorBrush = SolidColor(Color(0xFFDF3211)),
                textStyle = TextStyle(
                    color = Color(0xFF252A38),
                    fontSize = 11.sp
                ),
                decorationBox = { innerTextField ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (value.isEmpty()) {
                            Text(
                                text = stringResource(R.string.post_response_placeholder),
                                color = Color(0xFF9299A9),
                                fontSize = 11.sp
                            )
                        }
                        innerTextField()
                    }
                }
            )
            PrimaryButton(
                text = stringResource(R.string.post_response_action),
                onClick = onSubmit,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .height(46.dp),
                leadingIcon = Icons.Outlined.ChatBubbleOutline
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F6FA, widthDp = 393, heightDp = 852)
@Composable
private fun PostDetailsScreenPreview() {
    DesignSystemTheme {
        PostDetailsScreen()
    }
}