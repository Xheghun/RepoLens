package com.xheghun.repolens.di

import com.xheghun.repolens.data.api.GithubApiService
import com.google.gson.GsonBuilder
import com.xheghun.repolens.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.github.com/"

fun networkingModule() = module {
    single {
        HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
    }

    single {
        Interceptor { chain ->
            val originalRequest = chain.request();

            //Limit response to 40 items
            val modifiedUrl = originalRequest.url.newBuilder()
                .addQueryParameter("per_page", "40")
                .addQueryParameter("page", "1")
                .build()

            val modifiedRequest = originalRequest.newBuilder()
                .url(modifiedUrl)
                .addHeader("Authorization", "Bearer ${BuildConfig.GITHUB_ACCESS_TOKEN}")
                .addHeader("X-GitHub-Api-Version", "2022-11-28")
                .addHeader("Accept", "application/vnd.github+json")
                .build()

            chain.proceed(modifiedRequest)
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    } // client

    single { GsonBuilder().create() }

    single { GsonConverterFactory.create(get()) } // gson converter

    single {
        Retrofit.Builder()
            .addConverterFactory(get<GsonConverterFactory>())
            .client(get<OkHttpClient>())
            .baseUrl(BASE_URL)
            .build()
    } // retrofit

    single { get<Retrofit>().create(GithubApiService::class.java) } // api service
}