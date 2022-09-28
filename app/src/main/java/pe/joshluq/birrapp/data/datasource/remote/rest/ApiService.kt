package pe.joshluq.birrapp.data.datasource.remote.rest

import kotlinx.coroutines.Deferred
import pe.joshluq.birrapp.data.datasource.remote.rest.response.BeerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("beers")
    fun getBeersAsync(@Query("beer_name") name: String): Deferred<Response<List<BeerResponse>>>

}