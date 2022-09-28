package pe.joshluq.birrapp.util.fragment

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

interface BindableFragment<T : ViewBinding> {

    fun bind(inflater: LayoutInflater): T

}