package vcmsa.projects.apifusion.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import vcmsa.projects.apifusion.api.AgifyService
import vcmsa.projects.apifusion.api.GenderizeService
import vcmsa.projects.apifusion.api.KanyeService

object ApiClient {
    val agify: AgifyService = Retrofit.Builder()
        .baseUrl("https://api.agify.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(AgifyService::class.java)

    val genderize: GenderizeService = Retrofit.Builder()
        .baseUrl("https://api.genderize.io")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GenderizeService::class.java)

    val kanye: KanyeService = Retrofit.Builder()
        .baseUrl("https://api.kanye.rest")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(KanyeService::class.java)
}