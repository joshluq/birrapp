package pe.joshluq.birrapp.data.mapper

import pe.joshluq.birrapp.data.datasource.remote.rest.response.BeerResponse
import pe.joshluq.birrapp.domain.model.Beer

fun transformList(list: List<BeerResponse>?): List<Beer> =
    list.orEmpty().map(::transform)

private fun transform(response: BeerResponse) = Beer(
    id = response.id ?: throw IllegalArgumentException(),
    name = response.name.orEmpty(),
    description = response.description.orEmpty(),
    imageUrl = response.imageUrl.orEmpty(),
    abv = response.abv ?: 0.0
)