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
import android.app.Application
import android.os.Bundle

class OperaXInterceptorInitializer : OperaXInitializer() {
    override fun initialize(application: Application) {
        application.operaXInterceptorRegister()
    }
}

fun Application.operaXInterceptorRegister() {
    registerActivityLifecycleCallbacks(
            object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity?, bundle: Bundle?) = OperaXInterceptorObserver.notifyObservers(ON_CREATE(activity!!))
                override fun onActivityDestroyed(activity: Activity?) = OperaXInterceptorObserver.notifyObservers(ON_DESTROY(activity!!))
                override fun onActivityStarted(activity: Activity?) = OperaXInterceptorObserver.notifyObservers(ON_START(activity!!))
                override fun onActivityStopped(activity: Activity?) = OperaXInterceptorObserver.notifyObservers(ON_STOP(activity!!))
                override fun onActivityResumed(activity: Activity?) = OperaXInterceptorObserver.notifyObservers(ON_RESUME(activity!!))
                override fun onActivityPaused(activity: Activity?) = OperaXInterceptorObserver.notifyObservers(ON_PAUSE(activity!!))
                override fun onActivitySaveInstanceState(activity: Activity?, bundle: Bundle?) = Unit
            }
    )
}
