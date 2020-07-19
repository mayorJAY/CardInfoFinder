package com.josycom.mayorjay.cardinfofinder.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.josycom.mayorjay.cardinfofinder.databinding.FragmentCardInfoBinding
import kotlinx.android.synthetic.main.fragment_card_info.*
import org.koin.android.viewmodel.ext.android.viewModel

class CardInfoFragment : Fragment() {

    private val viewModel: CardInfoViewModel by viewModel()
    private lateinit var numberWatcher: TextWatcher
    private val progressDialog by lazy {
        ProgressDialog(requireContext()).apply {
            setMessage("Checking...")
            setCancelable(false)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentCardInfoBinding.inflate(layoutInflater).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        numberWatcher = object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val iin = p0?.subSequence(0, 7).toString()
                viewModel.getCardInfo(iin)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

        }
        card_number_input_editText.addTextChangedListener(numberWatcher)
    }

}