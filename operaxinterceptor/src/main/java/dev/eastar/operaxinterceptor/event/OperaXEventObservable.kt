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

import androidx.annotation.MainThread
import java.util.*

object OperaXEventObservable : Observable() {
    @MainThread
    @JvmStatic
    fun notify(obj: Any) {
        notifyObservers(obj)
    }

    override fun notifyObservers(data: Any) {
        if (data.javaClass.getAnnotation(OperaXEvent::class.java) == null)
            throw UnsupportedOperationException("!event obj must defined in the ${OperaXEvent::class.java}")

        setChanged()
        super.notifyObservers(data)
    }
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class OperaXEvent

@OperaXEvent
enum class OperaXEvents {
    EXIT, LOGOUT;
}

@OperaXEvent
data class LOGIN(val data: Any? = null)


