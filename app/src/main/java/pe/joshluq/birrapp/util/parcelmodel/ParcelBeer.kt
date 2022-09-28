package pe.joshluq.birrapp.util.parcelmodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ParcelBeer(
    val id: Int,
    val name: String,
    var description: String
) : Parcelable