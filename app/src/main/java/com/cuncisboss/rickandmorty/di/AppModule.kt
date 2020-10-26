package com.cuncisboss.rickandmorty.di

import android.content.Context
import androidx.room.Room
import com.cuncisboss.rickandmorty.data.local.CharacterDao
import com.cuncisboss.rickandmorty.data.local.CharacterDatabase
import com.cuncisboss.rickandmorty.data.remote.RickAndMorthyApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val DB_NAME = "RickAndMorty_db"

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): RickAndMorthyApi =
        Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(RickAndMorthyApi::class.java)

    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ): CharacterDatabase =
        Room.databaseBuilder(
            app,
            CharacterDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()


    @Singleton
    @Provides
    fun provideCharacterDao(db: CharacterDatabase): CharacterDao = db.characterDao()
}