package com.sagrd.flappyenel.ui.GameScreen.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SessionStorage(public val context: Context) {

    companion object{
        public val Context.dataStoree : DataStore<Preferences> by preferencesDataStore("USERSTORAGE")
        val USER = stringPreferencesKey("USER")
        val CLAVE = stringPreferencesKey("CLAVE")
    }

    val getUser: Flow<String?> = context.dataStoree.data.map { p ->
        p[USER]
    }

    suspend fun saveUser(value : String)
    {
        context.dataStoree.edit { preferences ->
            preferences[USER] = value

        }
    }

    val getClave: Flow<String?> = context.dataStoree.data.map { p ->
        p[CLAVE]
    }

    suspend fun saveClave(value : String)
    {
        context.dataStoree.edit { preferences ->
            preferences[CLAVE] = value

        }
    }




}