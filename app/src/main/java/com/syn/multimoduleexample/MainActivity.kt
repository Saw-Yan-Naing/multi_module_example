package com.syn.multimoduleexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.syn.multimoduleexample.ui.theme.MultiModuleExampleTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.Black.toArgb()),
            navigationBarStyle = SystemBarStyle.dark(Color.Black.toArgb())
        )
        setContent {
            MultiModuleExampleTheme {
                PingPongChecker()
            }
        }
    }
}


@Composable
fun PingPongChecker() {
    var pingStatus by remember { mutableStateOf(false) }
    var lastPingTime by remember { mutableLongStateOf(0L) }
    val coroutineScope = rememberCoroutineScope()

    val networkChecker by lazy { NetworkChecker.getInstance() }

    // Auto Ping Every Second
    LaunchedEffect(Unit) {
        while (true) {
            val startTime = System.currentTimeMillis()
            pingStatus = networkChecker.resolveHostname("google.com") // Google DNS for testing
            lastPingTime = System.currentTimeMillis() - startTime
            delay(1000) // Ping every 1 second
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(if (pingStatus) Color.Green else Color.Red)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Last Ping: $lastPingTime ms", fontSize = 18.sp)
    }
}
