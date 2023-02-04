package com.example.weatherapp.login.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val _viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservables()

        _binding?.btnLogin?.setOnClickListener {
            _viewModel.login(
                _binding?.edtEmail?.text.toString(),
                _binding?.edtPassword?.text.toString()
            )
        }

        _binding?.btnSignup?.setOnClickListener {
            findNavController().navigate(R.id.login_to_registration)
        }

        _viewModel.checkAuthentication()
    }

    private fun initObservables() {
        _viewModel.isAuthenticated.observe(viewLifecycleOwner) { isAuthenticated ->
            if (isAuthenticated) {
                findNavController().navigate(R.id.login_to_weather)
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