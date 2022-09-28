package pe.joshluq.birrapp.feature.findbeer

import android.view.LayoutInflater
import android.widget.EditText
import androidx.core.view.allViews
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import pe.joshluq.birrapp.databinding.FragmentFindBeerBinding
import pe.joshluq.birrapp.domain.model.Beer
import pe.joshluq.birrapp.util.eventhandler.EventObserver
import pe.joshluq.birrapp.util.fragment.BaseFragment
import pe.joshluq.birrapp.util.parcelmodel.mapper.toParcel
import pe.joshluq.birrapp.util.search.DebouncingQueryTextListener

@AndroidEntryPoint
class FindBeerFragment : BaseFragment<FragmentFindBeerBinding>() {

    private val viewModel by viewModels<FindBeerViewModel>()

    private val adapter by lazy { BeerListAdapter(::onBeerClicked) }

    override fun bind(inflater: LayoutInflater) = FragmentFindBeerBinding.inflate(inflater)

    override fun setupView() = with(binding) {
        beerListRecyclerView.addItemDecoration(createItemDecorator())
        beerListRecyclerView.adapter = adapter
        beerNameSearchView.setOnQueryTextListener(createQueryListener())
    }

    override fun setupObservables() = with(viewModel) {
        isLoading.observe(
            viewLifecycleOwner,
            EventObserver.createObserver(::onLoading)
        )
        beerList.observe(
            viewLifecycleOwner,
            EventObserver.createObserver(::onBeerListLoaded)
        )
        isSearchFieldEmpty.observe(
            viewLifecycleOwner,
            EventObserver.createObserver(::onSearchFieldEmpty)
        )
    }

    private fun onLoading(isLoading: Boolean) {
        val editText = binding.beerNameSearchView.allViews.find { it is EditText }
        when {
            isLoading -> {
                editText?.isEnabled = false
                binding.beerNameSearchView.requestFocus()
            }
            else -> {
                editText?.isEnabled = true
                editText?.requestFocus()
            }
        }
    }

    private fun onBeerListLoaded(list: List<Beer>) {
        adapter.submitList(list)
    }

    private fun onBeerClicked(beer: Beer) {
        val direction =
            FindBeerFragmentDirections.actionFindBeerFragmentToBeerDetailFragment(beer.toParcel())
        findNavController().navigate(direction)
    }

    private fun onSearchFieldEmpty(isEmpty: Boolean) {
        if (isEmpty) adapter.submitList(emptyList())
    }

    private fun createItemDecorator() = DividerItemDecoration(
        context,
        RecyclerView.VERTICAL
    )

    private fun createQueryListener() = DebouncingQueryTextListener(
        lifecycle,
        viewModel::findBeerByName
    )
}