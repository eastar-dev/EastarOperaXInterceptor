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
@file:Suppress("ClassName")

package dev.eastar.operaxinterceptor.interceptor

import android.app.Activity
import java.util.*

object OperaXInterceptorObserver : Observable() {
    fun notifyObservers(type: Type) {
        setChanged()
        super.notifyObservers(type)
    }
}

sealed class Type constructor(val activity: Activity) {
    override fun toString(): String = javaClass.simpleName + ", " + activity.javaClass.simpleName
}

class ON_CREATE constructor(activity: Activity) : Type(activity)
class ON_DESTROY constructor(activity: Activity) : Type(activity)
class ON_START constructor(activity: Activity) : Type(activity)
class ON_STOP constructor(activity: Activity) : Type(activity)
class ON_RESUME constructor(activity: Activity) : Type(activity)
class ON_PAUSE constructor(activity: Activity) : Type(activity)