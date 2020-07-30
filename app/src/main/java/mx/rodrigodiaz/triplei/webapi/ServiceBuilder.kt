package mx.rodrigodiaz.triplei.webapi


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import java.util.concurrent.TimeUnit


object ServiceBuilder {

    // Before release, change this URL to your live server URL such as "https://smartherd.com/"
    //private  var URL ="https://pilot-triplei.webteam.mx/team-service/"
    private  var URL ="http://www.webteam.mx/webapi2/"

    // Create Logger
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    // Create OkHttp Client
    private var okHttp = OkHttpClient.Builder()
        .readTimeout(23, TimeUnit.SECONDS)
        .connectTimeout(23, TimeUnit.SECONDS)
        .addInterceptor(logger)
    // OkHttpClient.Builder().addInterceptor(logger)


    var gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .setLenient()
        .create()
    // Create Retrofit Builder
    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        //.addConverterFactory(MoshiConverterFactory.create())
        //.addConverterFactory(MoshiConverterFactory.create())
        //.addCallAdapterFactory(CoroutineCallAdapterFactory())
        // .build()
        .client(okHttp.build())

    // Create Retrofit Instance
    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}