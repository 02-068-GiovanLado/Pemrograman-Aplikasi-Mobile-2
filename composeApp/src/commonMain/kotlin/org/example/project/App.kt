package org.example.project

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

data class News(
    val id: Int,
    val title: String,
    val category: String,
    val content: String
)

class NewsFeedViewModel {

    private val _readCount = MutableStateFlow(0)
    val readCount: StateFlow<Int> = _readCount.asStateFlow()

    private val _newsList = MutableStateFlow<List<String>>(emptyList())
    val newsList: StateFlow<List<String>> = _newsList.asStateFlow()

    fun getNewsFlow(): Flow<News> = flow {
        var id = 1
        val categories = listOf("Teknologi", "Olahraga", "Bisnis", "Kesehatan")
        val titles = listOf(
            "Kotlin Multiplatform Released",
            "Android Development Tips",
            "Mobile Programming Trends",
            "Cloud Computing Basics",
            "AI in Mobile Apps"
        )
        while (true) {
            delay(2000)
            val news = News(
                id = id++,
                title = titles.random(),
                category = categories.random(),
                content = "Full content for news $id..."
            )
            emit(news)
        }
    }

    suspend fun fetchNewsDetail(newsId: Int): String = withContext(Dispatchers.Default) {
        delay(1000)
        "Detail Berita $newsId: Ini adalah konten lengkap dari berita tersebut."
    }

    fun incrementReadCount() { _readCount.value++ }
    fun updateNewsList(news: String) { _newsList.value = _newsList.value + news }
    fun clearNews() { _newsList.value = emptyList() }
}

private val White        = Color(0xFFFFFFFF)
private val OffWhite     = Color(0xFFF5F5F5)
private val DividerGray  = Color(0xFFE0E0E0)
private val TextDark     = Color(0xFF1A1A1A)
private val TextMid      = Color(0xFF555555)
private val TextLight    = Color(0xFF888888)
private val AccentRed    = Color(0xFFD0021B)

private val TagTech      = Color(0xFF0047AB)
private val TagSports    = Color(0xFF217A3C)
private val TagBusiness  = Color(0xFFB45309)
private val TagHealth    = Color(0xFFD0021B)

fun categoryColor(cat: String) = when (cat) {
    "Teknologi" -> TagTech
    "Olahraga"  -> TagSports
    "Bisnis"    -> TagBusiness
    "Kesehatan" -> TagHealth
    else        -> TextMid
}

@Composable
fun App() {
    val viewModel = remember { NewsFeedViewModel() }
    val scope = rememberCoroutineScope()

    val readCount by viewModel.readCount.collectAsState()
    val newsList  by viewModel.newsList.collectAsState()

    var newsDetail       by remember { mutableStateOf<String?>(null) }
    var selectedCategory by remember { mutableStateOf("Teknologi") }
    var isRunning        by remember { mutableStateOf(false) }

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = White) {
            Column(modifier = Modifier.fillMaxSize()) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AccentRed)
                        .padding(horizontal = 24.dp, vertical = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "NewsFeed",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = White
                            )
                            Text(
                                text = "Simulator",
                                fontSize = 12.sp,
                                color = White.copy(alpha = 0.8f)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(White.copy(alpha = if (isRunning) 0.2f else 0.12f))
                                .padding(horizontal = 12.dp, vertical = 5.dp)
                        ) {
                            Text(
                                text = if (isRunning) "● LIVE" else "○ IDLE",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = White
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .background(Color(0xFFFF8A80))
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp, vertical = 20.dp)
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, DividerGray, RoundedCornerShape(8.dp))
                            .clip(RoundedCornerShape(8.dp))
                            .background(OffWhite),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        StatItem(modifier = Modifier.weight(1f), label = "Dibaca", value = "$readCount")
                        Box(modifier = Modifier.width(1.dp).height(56.dp).background(DividerGray))
                        StatItem(modifier = Modifier.weight(1f), label = "Berita", value = "${newsList.size}")
                        Box(modifier = Modifier.width(1.dp).height(56.dp).background(DividerGray))
                        StatItem(
                            modifier   = Modifier.weight(1f),
                            label      = "Kategori",
                            value      = selectedCategory,
                            valueColor = categoryColor(selectedCategory)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isRunning) TextDark else AccentRed)
                                .clickable {
                                    isRunning = !isRunning
                                    if (!isRunning) viewModel.clearNews()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (isRunning) "Stop" else "Start",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = White
                            )
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .border(1.5.dp, DividerGray, RoundedCornerShape(6.dp))
                                .background(White)
                                .clickable {
                                    selectedCategory =
                                        if (selectedCategory == "Teknologi") "Olahraga" else "Teknologi"
                                    viewModel.clearNews()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = selectedCategory,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = TextDark
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Latest News",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextDark
                        )
                        Text(
                            text = "${newsList.size} items",
                            fontSize = 13.sp,
                            color = TextLight
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Divider(color = AccentRed, thickness = 2.dp)
                    Spacer(modifier = Modifier.height(4.dp))

                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        items(newsList) { newsItem ->
                            NewsCard(
                                news    = newsItem,
                                onClick = {
                                    scope.launch {
                                        viewModel.incrementReadCount()
                                        val d = viewModel.fetchNewsDetail(newsItem.hashCode())
                                        newsDetail = d
                                    }
                                }
                            )
                            Divider(color = DividerGray, thickness = 1.dp)
                        }
                    }

                    newsDetail?.let { detail ->
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0xFFFFF8F8))
                                .border(1.dp, AccentRed.copy(alpha = 0.25f), RoundedCornerShape(6.dp))
                                .padding(horizontal = 16.dp, vertical = 14.dp)
                        ) {
                            Row(verticalAlignment = Alignment.Top) {
                                Box(
                                    modifier = Modifier
                                        .width(4.dp)
                                        .height(36.dp)
                                        .clip(RoundedCornerShape(2.dp))
                                        .background(AccentRed)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = detail,
                                    fontSize = 13.sp,
                                    color = TextMid,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(isRunning, selectedCategory) {
        if (isRunning) {
            viewModel.getNewsFlow()
                .filter { it.category == selectedCategory }
                .map { news -> "[${news.category}] ${news.title} (ID: ${news.id})" }
                .collect { viewModel.updateNewsList(it) }
        }
    }
}

@Composable
fun StatItem(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    valueColor: Color = TextDark
) {
    Column(
        modifier = modifier.padding(vertical = 14.dp, horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = valueColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            fontSize = 11.sp,
            color = TextLight
        )
    }
}

@Composable
fun NewsCard(
    news: String,
    onClick: () -> Unit
) {
    val category = news.substringAfter("[").substringBefore("]")
    val title    = news.substringAfter("] ").substringBefore(" (")
    val id       = news.substringAfter("(ID: ").substringBefore(")")
    val color    = categoryColor(category)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(3.dp))
                .background(color)
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = category,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                color = White,
                letterSpacing = 0.3.sp
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextDark,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(3.dp))
            Text(
                text = "ID $id",
                fontSize = 12.sp,
                color = TextLight
            )
        }

        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "›",
            fontSize = 22.sp,
            color = TextLight
        )
    }
}