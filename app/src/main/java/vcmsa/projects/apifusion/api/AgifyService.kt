package vcmsa.projects.apifusion.api

import retrofit2.http.GET
import retrofit2.http.Query
import vcmsa.projects.apifusion.model.AgifyResponse

interface AgifyService {
    @GET("/")
    suspend fun getAge(@Query("name") name: String): AgifyResponse
}

