package chat.app.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import chat.app.databinding.SignUpBinding
import chat.app.ui.viewModels.MainViewModels
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SignUp : Fragment() {
    private var _bind: SignUpBinding? = null
    private val bind get() = _bind
    private val viewModel by activityViewModels<MainViewModels>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _bind = SignUpBinding.inflate(layoutInflater)
        return bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.toast.onEach {
            if (it == "Success") {
                findNavController().navigateUp()
            }
        }.launchIn(lifecycleScope)

        backPoint()
        with(bind!!) {
            signUpButton.setOnClickListener {
                val name = name.text.toString().trim()
                val mobile = mobile.text.toString().trim()
                val password = password2.text.toString().trim()
                if (name.isEmpty()) {
                    viewModel.toastPoint("Please enter your name")
                } else if (mobile.isEmpty()) {
                    viewModel.toastPoint("Please enter your Mobile number")
                } else if (mobile.length != 10) {
                    viewModel.toastPoint("Please enter a valid Mobile number")
                } else if (password.isEmpty()) {
                    viewModel.toastPoint("Please enter your Password")
                } else {
                    viewModel.signUp(name = name, mobile = mobile, password = password)
                }
            }
        }
    }
}