package pe.joshluq.birrapp.util.resource

import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes resId: Int): String

}