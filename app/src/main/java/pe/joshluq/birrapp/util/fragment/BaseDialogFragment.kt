package pe.joshluq.birrapp.util.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import pe.joshluq.birrapp.R

abstract class BaseDialogFragment<T : ViewBinding> : DialogFragment(), BindableFragment<T> {

    protected lateinit var binding: T
        private set

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        bind(inflater).let(::setupBinding)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupEvents()
    }

    override fun getTheme() = R.style.Theme_App_Dialog

    private fun setupBinding(binding: T) {
        this.binding = binding
    }

    protected open fun setupView() {
        //Implement only if is required
    }

    protected open fun setupEvents() {
        //Implement only if is required
    }

}