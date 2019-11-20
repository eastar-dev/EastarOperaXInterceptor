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
package dev.eastar.operaxinterceptor.event

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.eastar.operaxinterceptor.interceptor.OperaXInitializer

class OperaXEventInitializer : OperaXInitializer() {
    override fun initialize(application: Application) {
        application.operaXEventRegister()
    }
}

fun Application.operaXEventRegister() {
    registerActivityLifecycleCallbacks(
            object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity?, bundle: Bundle?) {
                    (activity as? AppCompatActivity)?.run {
                        supportFragmentManager.registerFragmentLifecycleCallbacks(object : androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks() {
                            override fun onFragmentCreated(fm: androidx.fragment.app.FragmentManager, f: androidx.fragment.app.Fragment, savedInstanceState: Bundle?) {
                                (f as? OperaXEventObserver)?.let { OperaXEventObservable.addObserver(it) }
                            }

                            override fun onFragmentDestroyed(fm: androidx.fragment.app.FragmentManager, f: androidx.fragment.app.Fragment) {
                                (f as? OperaXEventObserver)?.let { OperaXEventObservable.deleteObserver(it) }
                            }
                        }, true)
                    }
                    (activity as? OperaXEventObserver)?.let { OperaXEventObservable.addObserver(it) }
                }

                override fun onActivityDestroyed(activity: Activity?) {
                    (activity as? OperaXEventObserver)?.let { OperaXEventObservable.deleteObserver(it) }
                }

                override fun onActivityStarted(activity: Activity?) {}
                override fun onActivityStopped(activity: Activity?) {}
                override fun onActivityResumed(activity: Activity?) {}
                override fun onActivityPaused(activity: Activity?) {}
                override fun onActivitySaveInstanceState(activity: Activity?, bundle: Bundle?) {}
            }
    )
}





