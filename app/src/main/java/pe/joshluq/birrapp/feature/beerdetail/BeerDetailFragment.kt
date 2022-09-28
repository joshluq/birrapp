package pe.joshluq.birrapp.feature.beerdetail

import android.view.LayoutInflater
import androidx.navigation.fragment.navArgs
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import pe.joshluq.birrapp.databinding.FragmentBeerDetailBinding
import pe.joshluq.birrapp.util.fragment.BaseFragment

@AndroidEntryPoint
class BeerDetailFragment : BaseFragment<FragmentBeerDetailBinding>() {

    private val args: BeerDetailFragmentArgs by navArgs()

    private val beer by lazy { args.beer }

    override fun bind(inflater: LayoutInflater) = FragmentBeerDetailBinding.inflate(inflater)

    override fun setupView() {
        binding.beerDescriptionTexView.text = beer.description
        binding.beerImageView.load(beer.imageUrl) {
            scale(Scale.FIT)
            crossfade(true)
        }
    }

}