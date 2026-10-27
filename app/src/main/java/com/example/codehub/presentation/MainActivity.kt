package com.example.codehub.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Home
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codehub.R
import com.example.ds.designsystem.components.BottomNavigation
import com.example.ds.designsystem.components.BottomNavigationItem
import com.example.ds.designsystem.components.CommunityCard
import com.example.ds.designsystem.components.PostCard
import com.example.ds.designsystem.components.SearchBar
import com.example.ds.designsystem.components.TopBar
import com.example.ds.designsystem.theme.DesignSystemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DesignSystemTheme {
                HomeScreen()
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun HomeScreenPreview() {
    DesignSystemTheme {
        HomeScreen()
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFF5F6FA),
        topBar = {
            Column {
                TopBar(
                    appName = stringResource(R.string.app_name),
                    initials = "LC"
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
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            WelcomeCard()

            Text(
                text = "Trending",
                color = Color(0xFF252A38),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("Kotlin", "Compose", "Android", "Git").forEach { topic ->
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = Color(0xFFFFFFFF),
                        border = BorderStroke(1.dp, Color(0xFFE1E5ED))
                    ) {
                        Text(
                            text = topic,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                            color = Color(0xFF4F5665),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            PostCard(
                authorName = stringResource(R.string.user_name),
                metadata = stringResource(R.string.post_date),
                title = stringResource(R.string.post_title),
                bodyParagraphs = listOf(
                    stringResource(R.string.post_body),
                    "Quem já passou por isso pode te ajudar com um passo a passo bem simples."
                ),
                tags = listOf("Git", "Team Work"),
                likesLabel = stringResource(R.string.comments),
                responsesLabel = stringResource(R.string.responses)
            )

            CommunityCard(
                authorName = stringResource(R.string.community_card_author),
                metadata = stringResource(R.string.community_card_meta),
                title = stringResource(R.string.community_card_title),
                summary = stringResource(R.string.community_card_summary),
                tags = listOf(
                    stringResource(R.string.community_card_tag_language),
                    stringResource(R.string.community_card_tag_level)
                ),
                actionLabel = stringResource(R.string.community_card_action),
                onOpenConversation = {}
            )
        }
    }
}

@Composable
private fun WelcomeCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color(0xFFFFF1E8),
        border = BorderStroke(1.dp, Color(0xFFFFD8C5))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFEB4D3D)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "LC",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(R.string.welcome_title),
                    color = Color(0xFF252A38),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.welcome_message),
                    color = Color(0xFF5D6677),
                    fontSize = 12.sp,
                    lineHeight = 18.sp
                )
            }
        }
    }
}