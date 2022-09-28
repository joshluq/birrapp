package pe.joshluq.birrapp.domain.repository

import pe.joshluq.birrapp.domain.model.Beer

interface BeerRepository {

    suspend fun findBeerByName(name: String): Result<List<Beer>>

}