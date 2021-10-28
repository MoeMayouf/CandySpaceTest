package com.mayouf.data.di

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mayouf.data.BuildConfig
import com.mayouf.data.api.StackExchangeApiService
import com.mayouf.data.datastore.StackExchangeRemoteDataStore
import com.mayouf.data.datastore.StackExchangeRemoteDataStoreImpl
import com.mayouf.data.mapper.DataStackExchangeToDomainStackExchangeMapper
import com.mayouf.data.mapper.DataStackExchangeToDomainStackExchangeMapperImpl
import com.mayouf.data.respository.StackExchangeRepositoryImpl
import com.mayouf.domain.repository.StackExchangeRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {
    @Singleton
    @Provides
    fun provideStackExchangeRemoteDataStore(
        apiService: StackExchangeApiService
    ): StackExchangeRemoteDataStore = StackExchangeRemoteDataStoreImpl(apiService)

    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        gsonConverterFactory: GsonConverterFactory,
    ) = Retrofit.Builder()
        .baseUrl("https://api.stackexchange.com/2.3/")
        .addConverterFactory(gsonConverterFactory)

    @Provides
    @Singleton
    fun provideHttpBuilder() =
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(httpLoggingInterceptor)
            }

            readTimeout(10L, TimeUnit.SECONDS)
            connectTimeout(10L, TimeUnit.SECONDS)
        }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(
        gson: Gson
    ): GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideConnectivityManager(context: Context): ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides
    @Reusable
    fun provideDataStackExchangeToDomainStackExchangeMapper(): DataStackExchangeToDomainStackExchangeMapper =
        DataStackExchangeToDomainStackExchangeMapperImpl()

    @Provides
    @Singleton
    fun provideStackExchangeRepository(
        spaceXRemoteSource: StackExchangeRemoteDataStore,
        dataStackExchangeToDomainStackExchangeMapper: DataStackExchangeToDomainStackExchangeMapper,
    ): StackExchangeRepository = StackExchangeRepositoryImpl(
        spaceXRemoteSource,
        dataStackExchangeToDomainStackExchangeMapper,
    )

    @Module
    companion object {
        @Provides
        @JvmStatic
        @Singleton
        internal fun provideApi(retrofit: Retrofit): StackExchangeApiService =
            retrofit.create(StackExchangeApiService::class.java)

        @Provides
        @JvmStatic
        @Singleton
        internal fun provideRetrofit(
            httpBuilder: OkHttpClient.Builder,
            retrofitBuilder: Retrofit.Builder, gson: Gson
        ): Retrofit = retrofitBuilder
            .client(httpBuilder.build())
            .addConverterFactory( GsonConverterFactory.create( gson ) )
            .build()
    }
}