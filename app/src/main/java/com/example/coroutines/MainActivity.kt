package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


private val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

          GlobalScope.launch(Dispatchers.IO) {
              Log.d(TAG, "Starting coroutine in IO thread${Thread.currentThread().name}")
              val answer = doNetworkCall()
              //Switching Coroutines Context
              withContext(Dispatchers.Main) {
                  Log.d(TAG, "Switching coroutine to  Main thread${Thread.currentThread().name}")
                  tv_txt.text = answer
              }
          }

        runBlocking {
            launch(Dispatchers.IO) {
                delay(3000L)
                Log.d(TAG,"Finished IO Coroutine 1")
            }
            launch(Dispatchers.IO) {
                delay(3000L)
                Log.d(TAG,"Finished IO Coroutine 2")
            }
            Log.d(TAG, "Start of runBlocking")
            delay(5000L)
            Log.d(TAG, "End of runBlocking ")
        }
        Log.d(TAG, "After runBlocking ")
    }


    suspend fun doNetworkCall(): String {
        delay(3000L)
        return "This is the Answer"
    }


}