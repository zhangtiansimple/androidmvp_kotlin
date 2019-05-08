package net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SourceFactory private constructor() {

    init {
        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()

        mRetrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun create(): RetrofitService {
        return mRetrofit.create(RetrofitService::class.java!!)
    }

    companion object {

        @Volatile
        private var mInstance: SourceFactory? = null

        val instance: SourceFactory?
            get() {
                if (mInstance == null) {
                    synchronized(SourceFactory::class.java) {
                        if (mInstance == null) {
                            mInstance = SourceFactory()
                        }
                    }
                }
                return mInstance
            }

        private lateinit var mRetrofit: Retrofit
    }
}
