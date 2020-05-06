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
import java.util.*

abstract class OperaXInterceptor : OperaXInitializer(), Observer {
    override fun onCreate(): Boolean {
        super.onCreate()
        OperaXInterceptorObserver.addObserver(this)
        android.util.Log.d("OperaXInterceptor", javaClass.name)
        return true
    }

    override fun update(observable: Observable, data: Any) {
        if ((data as Type).activity.javaClass.getAnnotation(OperaXSkip::class.java) != null) {
            return
        }

        try {
            when (data) {
                is ON_CREATE -> onCreate(data.activity)
                is ON_DESTROY -> onDestroy(data.activity)
                is ON_START -> onStart(data.activity)
                is ON_STOP -> onStop(data.activity)
                is ON_RESUME -> onResume(data.activity)
                is ON_PAUSE -> onPause(data.activity)
                else -> Unit //Log.e("!undefined message")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected open fun onCreate(activity: Activity) {}
    protected open fun onDestroy(activity: Activity) {}
    protected open fun onStart(activity: Activity) {}
    protected open fun onStop(activity: Activity) {}
    protected open fun onResume(activity: Activity) {}
    protected open fun onPause(activity: Activity) {}

    @Target(AnnotationTarget.CLASS)
    @Retention(AnnotationRetention.RUNTIME)
    @MustBeDocumented
    annotation class OperaXMain

    @Target(AnnotationTarget.CLASS)
    @Retention(AnnotationRetention.RUNTIME)
    @MustBeDocumented
    annotation class OperaXSkip
}

