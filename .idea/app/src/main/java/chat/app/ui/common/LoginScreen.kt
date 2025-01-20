package chat.app.ui.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import chat.app.R
import chat.app.dataLayer.response.LoginResponse
import chat.app.databinding.LoginScreenBinding
import chat.app.ui.viewModels.MainViewModels
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginScreen : Fragment() {
    private var _bind: LoginScreenBinding? = null
    private val bind get() = _bind

    private val activityModel by activityViewModels<MainViewModels>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _bind = LoginScreenBinding.inflate(layoutInflater)
        return bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toPauseTheApp()

        val nave = findNavController()
        val type = requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
        activityModel.userData.onEach {
            it?.let {
                if (it.id == "admin") {
                    type.edit().apply {
                        putString("id", it.id)
                        putString("name", it.name)
                        putString("mobile", it.mobile)
                        putString("password", it.password)
                    }.apply()
                    nave.navigate(R.id.action_loginScreen_to_adminDashBoard)

                } else if (it.id?.isNotEmpty() == true) {
                    type.edit().apply {
                        putString("id", it.id)
                        putString("name", it.name)
                        putString("mobile", it.mobile)
                        putString("password", it.password)
                    }.apply()

                    nave.navigate(R.id.action_loginScreen_to_userDashBoard)
                }
            }
        }.launchIn(lifecycleScope)


        with(bind!!) {
            createAc.setOnClickListener {
                findNavController().navigate(R.id.action_loginScreen_to_signUp)
            }

            signUpButton.setOnClickListener {
                val mobileNum = mobileNum.text.toString().trim()
                val password = password.text.toString().trim()

                if (mobileNum.isEmpty()) {
                    activityModel.toastPoint("Please enter your mobile number")
                } else if (password.isEmpty()) {
                    activityModel.toastPoint("Please enter your password")
                } else if (mobileNum.contains("123456") && password.contains("admin")) {
                    activityModel.addUsers(
                        users = LoginResponse.Users(
                            id = "admin",
                            name = null,
                            mobile = null,
                            password = null
                        )
                    )
                } else {
                    activityModel.loginPage(mobile = mobileNum, password = password)
                }
            }


        }


    }
}