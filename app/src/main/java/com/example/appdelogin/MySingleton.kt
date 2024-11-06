package com.example.appdelogin

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class MySingleton private constructor(context: Context) {
    private var requestQueue: RequestQueue? = null
    private val ctx: Context = context.applicationContext

    companion object {
        @Volatile
        private var instance: MySingleton? = null

        fun getInstance(context: Context): MySingleton {
            return instance ?: synchronized(this) {
                instance ?: MySingleton(context).also { instance = it }
            }
        }
    }

    fun getRequestQueue(): RequestQueue {
        return requestQueue ?: synchronized(this) {
            requestQueue ?: Volley.newRequestQueue(ctx).also { requestQueue = it }
        }
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        getRequestQueue().add(req)
    }
}
