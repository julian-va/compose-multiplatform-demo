package jva.cloud.democomposemultiplatform.di

import io.ktor.client.engine.darwin.Darwin
import jva.cloud.democomposemultiplatform.utils.ConstantApp.DATA_STORE_FILE_NAME
import jva.cloud.democomposemultiplatform.utils.ConstantApp.QUALIFIER_DATASTORE_PATH
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask


@OptIn(ExperimentalForeignApi::class)
actual val platformModule = module {
    single { Darwin.create() }

    single<String>(qualifier = QUALIFIER_DATASTORE_PATH) {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        requireNotNull(documentDirectory).path + "/$DATA_STORE_FILE_NAME"
    }
}