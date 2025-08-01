package vcmsa.projects.apifusion.api

import retrofit2.http.GET
import vcmsa.projects.apifusion.model.KanyeResponse

interface KanyeService {
    @GET("/")
    suspend fun getQuote(): KanyeResponse
}