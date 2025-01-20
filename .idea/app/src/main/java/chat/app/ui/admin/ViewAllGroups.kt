package chat.app.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import chat.app.R
import chat.app.databinding.UserGroupsBinding
import chat.app.ui.common.backPoint
import chat.app.ui.users.adapters.GroupView
import chat.app.ui.viewModels.MainViewModels
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ViewAllGroups : Fragment() {
    private var _bind: UserGroupsBinding? = null
    private val bind get() = _bind
    private val model by activityViewModels<MainViewModels>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _bind = UserGroupsBinding.inflate(layoutInflater)
        return bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(bind!!) {
            backPoint()
            textView5.isVisible = true
            model.getDetails()
            model.group.onEach { groups ->
                cycle2.adapter = GroupView(requireContext(), groups) {
                    val bundle = Bundle()
                    bundle.putParcelable("Groups", it)
                    findNavController().navigate(R.id.action_viewAllGroups_to_chatArea, bundle)
                }
            }.launchIn(lifecycleScope)
        }
    }
}