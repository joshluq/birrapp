package pe.joshluq.birrapp.util.dialog

import android.view.LayoutInflater
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import pe.joshluq.birrapp.databinding.DialogSimpleMessageBinding
import pe.joshluq.birrapp.util.fragment.BaseDialogFragment

class SimpleMessageDialog : BaseDialogFragment<DialogSimpleMessageBinding>() {

    private val args: SimpleMessageDialogArgs by navArgs()

    override fun bind(inflater: LayoutInflater) = DialogSimpleMessageBinding.inflate(inflater)

    override fun setupView() {
        binding.tvMessage.text = args.message
    }

    override fun setupEvents() {
        binding.btnDone.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}