package jva.cloud.democomposemultiplatform.di

import io.ktor.client.engine.okhttp.OkHttp
import jva.cloud.democomposemultiplatform.utils.ConstantApp.DATA_STORE_FILE_NAME
import jva.cloud.democomposemultiplatform.utils.ConstantApp.QUALIFIER_DATASTORE_PATH
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule = module {
    single { OkHttp.create() }
    
    single<String>(qualifier = QUALIFIER_DATASTORE_PATH) {
        val context = androidContext()
        context.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath
    }

}