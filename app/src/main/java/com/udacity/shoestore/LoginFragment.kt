package com.udacity.shoestore

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        binding.loginButton.setOnClickListener { view: View ->
            handleLogin(binding, view)
        }

        binding.signupButton.setOnClickListener {view: View ->
            handleLogin(binding, view)
        }

        return binding.root
    }

    private fun handleLogin(
        binding: FragmentLoginBinding,
        view: View
    ) {
        if (binding.emailFieldEdittext.text.isValidEmail()) {
            Navigation.findNavController(view)
                .navigate(R.id.action_loginFragment_to_welcomeFragment)
        } else {
            Toast.makeText(context, "Please Enter a valid Email", Toast.LENGTH_SHORT).show()
        }
    }
}

private fun Editable.isValidEmail(): Boolean =
    """([a-z0-9_.-]+)@([a-z0-9_.-]+)\.([a-z.]{2,6})""".toRegex().matches(this)
