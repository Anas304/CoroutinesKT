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


    }


    suspend fun doNetworkCall(): String {
        delay(3000L)
        return "This is the Answer"
    }


}