package com.example.weatherapp.registration.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val _viewModel: RegistrationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservables()

        _binding?.btnSignup?.setOnClickListener {
            _viewModel.register(
                _binding?.edtName?.text.toString(),
                _binding?.edtEmail?.text.toString(),
                _binding?.edtPassword?.text.toString(),
                _binding?.edtConfirmPassword?.text.toString()
            )
        }
    }

    private fun initObservables() {
        _viewModel.isRegistered.observe(viewLifecycleOwner) { isRegistered ->
            if (isRegistered) {
                findNavController().navigate(R.id.registration_to_weather)
            }
        }

        _viewModel.message.observe(viewLifecycleOwner) { message ->
            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context?.applicationContext, message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}