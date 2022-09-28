package pe.joshluq.birrapp.data.repository

import pe.joshluq.birrapp.data.datasource.remote.rest.ApiService
import pe.joshluq.birrapp.data.datasource.remote.rest.handleResponse
import pe.joshluq.birrapp.data.mapper.transformList
import pe.joshluq.birrapp.domain.model.Beer
import pe.joshluq.birrapp.domain.repository.BeerRepository
import javax.inject.Inject

class BeerDataRepository @Inject constructor(
    private val api: ApiService,
) : BeerRepository {

    override suspend fun findBeerByName(name: String): Result<List<Beer>> = handleResponse {
        api.getBeersAsync(name.replace(" ", "_"))
    }.map(::transformList)


}