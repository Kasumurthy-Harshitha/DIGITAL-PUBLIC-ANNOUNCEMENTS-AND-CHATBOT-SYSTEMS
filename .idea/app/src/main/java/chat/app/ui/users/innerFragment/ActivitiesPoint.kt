package chat.app.ui.users.innerFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import chat.app.R
import chat.app.databinding.UserActivitiesBinding
import chat.app.ui.users.adapters.GroupView
import chat.app.ui.users.innerFragment.interFacePoint.Carrier
import chat.app.ui.viewModels.MainViewModels
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ActivitiesPoint(val carrier: Carrier) : Fragment() {
    private var _bind: UserActivitiesBinding? = null
    private val bind get() = _bind


    private val models by activityViewModels<MainViewModels>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _bind = UserActivitiesBinding.inflate(layoutInflater)
        return bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(bind!!) {
            logout.setOnClickListener {
                carrier.logout()
            }
        }
    }
}