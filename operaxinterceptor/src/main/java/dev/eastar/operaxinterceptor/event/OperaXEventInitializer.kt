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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
import dev.eastar.operaxinterceptor.interceptor.OperaXInitializer

class OperaXEventInitializer : OperaXInitializer() {
    override fun initialize(application: Application) {
        application.operaXEventRegister()
    }
}

fun Application.operaXEventRegister() {
    registerActivityLifecycleCallbacks(
            object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
                    if (activity is OperaXEventObserver) OperaXEventObservable.addObserver(activity)
                    if (activity is AppCompatActivity) registerFragment(activity)
                }

                override fun onActivityDestroyed(activity: Activity) {
                    if (activity is OperaXEventObserver) OperaXEventObservable.deleteObserver(activity)
                }


                fun registerFragment(activity: AppCompatActivity) {
                    activity.supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentLifecycleCallbacks() {
                        override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
                            if (f is OperaXEventObserver) OperaXEventObservable.addObserver(f)
                        }

                        override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
                            if (f is OperaXEventObserver) OperaXEventObservable.deleteObserver(f)
                        }
                    }, true)
                }

                override fun onActivityStarted(activity: Activity) {}
                override fun onActivityStopped(activity: Activity) {}
                override fun onActivityResumed(activity: Activity) {}
                override fun onActivityPaused(activity: Activity) {}
                override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle) {}
            }
    )
}





