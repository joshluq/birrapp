package pe.joshluq.birrapp.util.parcelmodel.mapper

import pe.joshluq.birrapp.domain.model.Beer
import pe.joshluq.birrapp.util.parcelmodel.ParcelBeer

fun Beer.toParcel() = ParcelBeer(
    id = id,
    name = name,
    description = description,
)
