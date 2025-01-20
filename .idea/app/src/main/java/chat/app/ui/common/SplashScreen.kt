package chat.app.ui.common

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import chat.app.R
import chat.app.databinding.SplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment() {
    private val bind by lazy {
        SplashScreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(bind.splashImage) {
            alpha = 0f
            val nav= findNavController()
            val type = requireActivity().getSharedPreferences("user",Context.MODE_PRIVATE).getString("id","")
            animate().setDuration(1000).alpha(1f).withEndAction {
                if (type == "admin") {
                    nav.navigate(R.id.action_splashScreen_to_adminDashBoard)
                } else if (type?.isNotEmpty() == true) {
                    nav.navigate(R.id.action_splashScreen_to_userDashBoard)
                }else {
                    nav.clearBackStack(R.id.adminDashBoard)
                    nav.clearBackStack(R.id.userDashBoard)
                    nav.navigate(R.id.action_splashScreen_to_loginScreen)
                }
            }
        }

    }
}