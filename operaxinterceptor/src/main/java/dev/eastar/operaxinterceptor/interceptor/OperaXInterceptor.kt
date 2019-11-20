/*
 * Copyright 2019 copyright eastar Jeong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.eastar.operaxinterceptor.interceptor

import android.app.Activity
//import android.log.Log

import java.util.*

abstract class OperaXInterceptor : OperaXInitializer(), Observer {
    interface IMain

    override fun onCreate(): Boolean {
//        Log.e("OperaXInterceptor", "reg", javaClass.simpleName)
        OperaXInterceptorObserver.addObserver(this)
        return super.onCreate()
    }

    override fun update(observable: Observable, data: Any) {
//        Log.w("OperaXInterceptor", javaClass.simpleName, "<<", data)
        try {
            when (val type = data as Type) {
                is ON_CREATE -> onCreate(type.activity)
                is ON_DESTROY -> onDestroy(type.activity)
                is ON_START -> onStart(type.activity)
                is ON_STOP -> onStop(type.activity)
                is ON_RESUME -> onResume(type.activity)
                is ON_PAUSE -> onPause(type.activity)
                else -> Unit //Log.e("!undefined message")
            }
        } catch (e: Exception) {
        }
    }

    protected open fun onCreate(activity: Activity) {}
    protected open fun onDestroy(activity: Activity) {}
    protected open fun onStart(activity: Activity) {}
    protected open fun onStop(activity: Activity) {}
    protected open fun onResume(activity: Activity) {}
    protected open fun onPause(activity: Activity) {}

}

