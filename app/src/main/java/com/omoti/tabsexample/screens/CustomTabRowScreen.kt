package com.omoti.tabsexample.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.omoti.tabsexample.ui.theme.TabsExampleTheme

/**
 * Customized Tab Sample
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTabRowScreen(onBack: () -> Unit, initialTabIndex: Int = 0) {
    var selectedTabIndex by remember { mutableStateOf(initialTabIndex) }
    val titles = listOf("Tab 1", "Tab 2", "Tab 3")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Custom TabRow") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            TabRow(
                selectedTabIndex = selectedTabIndex,
                indicator = { tabPositions -> CustomIndicator(tabPositions[selectedTabIndex]) },
            ) {
                titles.forEachIndexed { index, title ->
                    CustomTab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = title,
                        badgeNumber = index,
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Text tab ${selectedTabIndex + 1} selected",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }
}

@Composable
fun CustomIndicator(tabPosition: TabPosition) {
    Box(
        modifier = Modifier
            .tabIndicatorOffset(tabPosition)
            .height(3.dp)
            .padding(horizontal = 32.dp)
            .clip(RoundedCornerShape(3.dp, 3.dp, 0.dp, 0.dp))
            .background(color = Color.Blue),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CustomTab(
    selected: Boolean,
    onClick: () -> Unit,
    text: String,
    badgeNumber: Int = 0,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        BadgedBox(
            badge = {
                if (badgeNumber > 0) {
                    Badge { Text(text = badgeNumber.toString()) }
                }
            },
        ) {
            Text(
                text = text,
                color = if (selected) Color.Blue else Color.Gray,
                textAlign = TextAlign.Center,
                maxLines = 1,
            )
        }
    }
}

@Preview
@Composable
private fun CustomTabRowScreenPreview() {
    TabsExampleTheme {
        CustomTabRowScreen(onBack = {})
    }
}

@Preview
@Composable
fun CustomTabPreview() {
    TabsExampleTheme {
        Column(modifier = Modifier.background(color = Color.White)) {
            CustomTab(selected = true, onClick = {}, text = "Tab 1", 0)
            CustomTab(selected = true, onClick = {}, text = "Tab 1", 1)
            CustomTab(selected = true, onClick = {}, text = "Tab 1", 999)
        }
    }
}