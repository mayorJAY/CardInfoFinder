package com.josycom.mayorjay.cardinfofinder.ui

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.josycom.mayorjay.cardinfofinder.R
import com.josycom.mayorjay.cardinfofinder.databinding.FragmentCardInfoBinding
import com.josycom.mayorjay.cardinfofinder.utils.ApiError
import com.josycom.mayorjay.cardinfofinder.utils.Result
import com.josycom.mayorjay.cardinfofinder.utils.snack
import kotlinx.android.synthetic.main.fragment_card_info.*
import org.koin.android.viewmodel.ext.android.viewModel

class CardInfoFragment : Fragment() {

    private val viewModel: CardInfoViewModel by viewModel()
    private lateinit var numberWatcher: TextWatcher
    private val progressDialog by lazy {
        ProgressDialog(requireContext()).apply {
            setMessage(getString(R.string.checking))
            setCancelable(false)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentCardInfoBinding.inflate(layoutInflater).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        numberWatcher = object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            // Watch the imputed card number and trigger the network call
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().length == 14 || p0.toString().length == 15) {
                    card_number_input_layout.error = getString(R.string.less_number_error_msg)
                } else if (p0.toString().length == 16) {
                    viewModel.getCardInfo(p0?.subSequence(0, 8).toString())
                    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(card_info_layout.windowToken, 0)
                } else if (p0.toString().length > 16) {
                    card_number_input_layout.error = getString(R.string.excess_number_error_msg)
                }
            }
        }
        card_number_input_editText.addTextChangedListener(numberWatcher)
        observeCheck()
        observeCardInfo()
    }

    // Check the result of the network call and display content on the screen as appropriate
    private fun observeCheck() {
        viewModel.checkCard.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Loading -> {
                    progressDialog.show()
                }
                is Result.Success -> {
                    progressDialog.hide()
                }
                is Result.Error -> {
                    progressDialog.hide()
                    val msg = ApiError(result.exception).message
                    card_info_layout.snack(message = msg, length = Snackbar.LENGTH_LONG)
                }
            }
        })
    }

    // Get the card info and show on the screen
    private fun observeCardInfo() {
        viewModel.cardInfo.observe(viewLifecycleOwner, Observer { cardInfo ->
            tv_card_brand.text = cardInfo.brand
            tv_card_type.text = cardInfo.type.capitalize()
            tv_bank_name.text = cardInfo.bank.name
            tv_country.text = cardInfo.country.name
        })
    }
}