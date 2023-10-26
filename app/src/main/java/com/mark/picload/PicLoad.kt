package com.mark.picload

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

object PicLoad {

    private val handler = Handler(Looper.getMainLooper())
    fun loadImageFromUrl(url:String,
                         onSuccess : (Bitmap) -> Unit,
                         onError :(String) ->Unit
    ) {
        thread(start = true){
            try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val inputString : InputStream = connection.inputStream
            val bitmap = BitmapFactory.decodeStream(inputString)
            if (bitmap!=null){
                handler.post{onSuccess(bitmap)}
             }
            else{

                onError("Error !")

            }
            }catch (e:Exception){
                e.printStackTrace()
                onError("Error @")
            }
        }
    }
}