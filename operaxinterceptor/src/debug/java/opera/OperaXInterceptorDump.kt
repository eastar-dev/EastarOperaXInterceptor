package opera

import android.app.Application
import dalvik.system.DexFile
import dev.eastar.operaxinterceptor.interceptor.OperaXInterceptor
import java.util.concurrent.Executors

@Suppress("unused", "DEPRECATION")
fun Application.operaXInterceptorDump() {
    Executors.newSingleThreadExecutor().execute {
        runCatching {
            val parentClass = OperaXInterceptor::class.java
            val packageName = parentClass.`package`?.name!!
            val thisClass = parentClass.name
            DexFile(packageCodePath).entries().toList()
                    .filter { it.startsWith(packageName) }
                    .filterNot { it.startsWith(thisClass) }
                    .filterNot { it.contains('$') }
                    .map { Class.forName(it) }
                    .filter {
                        parentClass.isAssignableFrom(it) }
                    .forEach { android.util.Log.w("OperaXInterceptorDump", it.toString()) }
        }
    }
}