package vcmsa.projects.apifusion.api

import retrofit2.http.GET
import retrofit2.http.Query
import vcmsa.projects.apifusion.model.GenderizeResponse

interface GenderizeService {
    @GET("/")
    suspend fun getGender(@Query("name") name: String): GenderizeResponse
}