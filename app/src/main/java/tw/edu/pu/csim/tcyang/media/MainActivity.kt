package tw.edu.pu.csim.tcyang.media

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tw.edu.pu.csim.tcyang.media.ui.theme.MediaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MediaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var Animals = arrayListOf(R.drawable.animal0, R.drawable.animal1,
        R.drawable.animal2, R.drawable.animal3,
        R.drawable.animal4, R.drawable.animal5,
        R.drawable.animal6, R.drawable.animal7,
        R.drawable.animal8, R.drawable.animal9)

    var AnimalsName = arrayListOf("鴨子","企鵝","青蛙","貓頭鷹","海豚", "牛", "無尾熊", "獅子", "狐狸", "小雞")

    val context = LocalContext.current
    var mper = MediaPlayer()

    Column {
        Row{
            Button(onClick = {
                mper.reset()
                mper = MediaPlayer.create(context, R.raw.tcyang)
                mper.start() },
                Modifier
                    .fillMaxWidth(0.33f)
                    .fillMaxHeight(0.3f),
                colors = buttonColors(Color.Green)

            ) {
                Text(text = "歡迎", color = Color.Blue)
                Text(text = "修課", color = Color.Red)
                Image(
                    painterResource(id = R.drawable.teacher),
                    contentDescription ="teacher icon",
                    modifier = Modifier.size(50.dp))
            }

            Button(
                onClick = {
                    mper.reset()
                    mper = MediaPlayer.create(context, R.raw.fly)
                    mper.start()
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.5f)
            ) {
                Text(text = "展翅飛翔")
                Image(
                    painterResource(id = R.drawable.fly),
                    contentDescription ="fly icon",
                    modifier = Modifier.size(50.dp))
            }

            Button(
                onClick = {
                    mper.reset()
                    context.startActivity(Intent(context, VideoActivity::class.java))
                },
                Modifier
                    .fillMaxWidth ()
                    .fillMaxHeight(0.7f),
                colors = buttonColors(Color.Red)
            ) {
                Text(text = "手碟音樂", color = Color.White)
                Image(
                    painterResource(id = R.drawable.handpan),
                    contentDescription ="handpan icon",
                    modifier = Modifier.size(50.dp))
            }

        }


        LazyRow {
            items(51) { index ->
                Text(text = AnimalsName[index % 10])
                Image(
                    painter = painterResource(id = Animals[index % 10]),
                    contentDescription = "可愛動物圖片",
                    modifier = Modifier.fillParentMaxWidth(1.0f))
            }
            item{
                Text(text = "行動應用軟體開發")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MediaTheme {
        Greeting("Android")
    }
}