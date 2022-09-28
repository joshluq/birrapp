package pe.joshluq.birrapp.util.parcelmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ParcelBeer(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val abv: Double,
) : Parcelable