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

import android.os.Looper
import java.util.*

object OperaXEventObservable : Observable() {
    @JvmStatic
    fun notify(obj: Enum<*>) {
        notifyObservers(obj)
    }

    override fun notifyObservers(data: Any?) {
        if (data == null)
            throw NullPointerException("!event obj must not null")

        if (data.javaClass.name != "smart.base.EE")
            throw NullPointerException("!event obj must defined in the smart.base.EE")

        if (data !is Enum<*>)
            throw NullPointerException("!event obj must Enum")

        if (Looper.myLooper() != Looper.getMainLooper())
            throw IllegalThreadStateException("!event obj must be in MainThread")

        setChanged()
        super.notifyObservers(data)
    }

}
