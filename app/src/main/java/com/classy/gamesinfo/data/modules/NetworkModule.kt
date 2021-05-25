package com.classy.gamesinfo.data.modules

import com.classy.gamesinfo.BuildConfig
import com.classy.gamesinfo.data.api.ApiHelper
import com.classy.gamesinfo.data.api.GameSpotService
import com.classy.gamesinfo.data.api.RetrofitBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single { ApiHelper( get() ) }
    single { RetrofitBuilder }
    single { provideGameSpotApi ( get() ) }
    single { provideRetrofit ( get() ) }
    single { provideOkHttpClient() }
    
}

fun provideOkHttpClient(): OkHttpClient {
    //return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
    return OkHttpClient().newBuilder().build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()

}

//val networkModule = module {
//    factory { AuthInterceptor() }
//    factory { provideOkHttpClient(get()) }
//    factory { provideForecastApi(get()) }
//    single { provideRetrofit(get()) }
//}
//
//fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
//        .addConverterFactory(GsonConverterFactory.create()).build()
//}
//
//fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
//    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
//}
//
fun provideGameSpotApi(retrofit: Retrofit): GameSpotService = retrofit.create(GameSpotService::class.java)