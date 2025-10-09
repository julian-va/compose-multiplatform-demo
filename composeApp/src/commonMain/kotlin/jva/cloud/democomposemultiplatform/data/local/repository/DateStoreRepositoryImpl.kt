package jva.cloud.democomposemultiplatform.data.local.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import jva.cloud.democomposemultiplatform.domain.repository.DateStoreRepository
import kotlinx.coroutines.flow.first

class DateStoreRepositoryImpl(private val dataStore: DataStore<Preferences>) : DateStoreRepository {


    override suspend fun save(key: String, value: String) {
        dataStore.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    override suspend fun read(key: String): String? {
        return getPreferences()[stringPreferencesKey(name = key)]
    }

    override suspend fun deleted(key: String) {
        dataStore.edit {
            it.remove(stringPreferencesKey(key))
        }
    }

    private suspend fun getPreferences(): Preferences {
        return dataStore.data.first()
    }
}
