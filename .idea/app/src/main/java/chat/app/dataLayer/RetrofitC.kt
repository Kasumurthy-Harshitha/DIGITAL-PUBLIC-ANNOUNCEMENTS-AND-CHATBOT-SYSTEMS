package chat.app.dataLayer

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitC {

    private val BASEURl = "https://wizzie.online/ChatBot/"

    private val okhttp = okhttp3.OkHttpClient.Builder().addInterceptor {
        chain->
        chain.withReadTimeout(10,TimeUnit.SECONDS)
        chain.withConnectTimeout(10,TimeUnit.SECONDS)
        chain.withConnectTimeout(10,TimeUnit.SECONDS)
        chain.proceed(chain.request())
    }.build()

    fun api()=Retrofit.Builder().let {
        it.client(okhttp)
        it.baseUrl(BASEURl)
        it.addConverterFactory(GsonConverterFactory.create())
        it.build().create(Api::class.java)
    }

}