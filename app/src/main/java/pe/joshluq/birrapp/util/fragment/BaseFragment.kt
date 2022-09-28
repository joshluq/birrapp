package pe.joshluq.birrapp.util.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment(), BindableFragment<T> {

    protected lateinit var binding: T
        private set

    private val viewRoot by lazy { binding.root }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        bind(inflater).let(::setupBinding)
        return viewRoot
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupEvents()
        setupObservables()
    }

    private fun setupBinding(binding: T) {
        this.binding = binding
    }

    protected open fun setupView() {
        //Implement only if is required
    }

    protected open fun setupObservables() {
        //Implement only if is required
    }

    protected open fun setupEvents() {
        //Implement only if is required
    }

}