package com.mark.picload

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import com.mark.picload.ui.theme.PicLoadTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PicLoadTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ImageComponent("https://molo17.com/wp-content/uploads/2021/11/StudioCompose10.jpg")

                 }
            }
        }
    }
}
@Composable
fun ImageComponent(url:String){
var isLoading by remember {
mutableStateOf(true)
}
    var bitmap by remember {
        mutableStateOf<Bitmap?>(null) }
    if (bitmap==null&&isLoading)
    {
        PicLoad.loadImageFromUrl(url,
            onSuccess = {loadedBitmap->
                        bitmap = loadedBitmap
            isLoading=false
        }, onError = {
            isLoading=false
        })
    }
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center ,
        content = {
            if (isLoading){
                CircularProgressIndicator()
            }else{
                bitmap?.also {
                    Image(bitmap = it.asImageBitmap(), contentDescription ="Loaded Image" , modifier = Modifier.fillMaxHeight(), contentScale = ContentScale.Fit)
                }
            }
        })
}

