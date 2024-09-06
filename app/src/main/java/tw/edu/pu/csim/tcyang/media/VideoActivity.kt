package tw.edu.pu.csim.tcyang.media

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import tw.edu.pu.csim.tcyang.media.ui.theme.MediaTheme

class VideoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MediaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val link = "https://www1.pu.edu.tw/~tcyang/handpan.mp4"
    val exoPlayer = ExoPlayer.Builder(context).build()

    val mediaItem = MediaItem.fromUri(android.net.Uri.parse(link))
    exoPlayer.setMediaItem(mediaItem)

    val playerView = PlayerView(context)
    playerView.player = exoPlayer

    DisposableEffect(AndroidView(factory = {playerView})){
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true

        onDispose{
            exoPlayer.release()
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MediaTheme {
        Greeting2("Android")
    }
}