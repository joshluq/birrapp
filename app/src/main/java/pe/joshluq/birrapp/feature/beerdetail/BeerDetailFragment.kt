package pe.joshluq.birrapp.feature.beerdetail

import android.view.LayoutInflater
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import pe.joshluq.birrapp.databinding.FragmentBeerDetailBinding
import pe.joshluq.birrapp.util.fragment.BaseFragment

@AndroidEntryPoint
class BeerDetailFragment : BaseFragment<FragmentBeerDetailBinding>() {

    val args: BeerDetailFragmentArgs by navArgs()

    override fun bind(inflater: LayoutInflater) = FragmentBeerDetailBinding.inflate(inflater)

    override fun setupView() {
        binding.beerDescriptionTexView.text = args.beer.description
    }

}