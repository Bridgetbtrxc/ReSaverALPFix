package com.elflin.movieapps.retrofit

import android.content.Context
import com.elflin.movieapps.data.DataStoreManager
import com.elflin.movieapps.data.EndPointAPI
import com.elflin.movieapps.helper.Const
import com.elflin.movieapps.retrofit.RetrofitInstance.api
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

import dagger.hilt.android.qualifiers.ApplicationContext


@Module
@InstallIn(SingletonComponent::class)
object AppModule {




    @Singleton
    @Provides
    fun getRetrofitServiceInstance(retrofit: Retrofit):
            EndPointAPI {
        return retrofit.create(EndPointAPI::class.java)
    }
@Singleton
    @Provides
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }
    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                val token = sharedPreferences.getString("token", "") ?: ""
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(request)
            }
        }.build()
    }



    @Singleton
    @Provides
    fun getRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(Const.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}