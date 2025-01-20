package chat.app.ui.users

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import chat.app.R
import chat.app.dataLayer.response.GroupsResponse
import chat.app.databinding.UserDashBoardBinding
import chat.app.ui.common.spanned
import chat.app.ui.common.toPauseTheApp
import chat.app.ui.users.adapters.ControllerAdapt
import chat.app.ui.users.innerFragment.interFacePoint.Carrier
import chat.app.ui.viewModels.MainViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.tabs.TabLayoutMediator

class UserDashBoard : Fragment(), Carrier {
    private var _bind: UserDashBoardBinding? = null
    private val bind get() = _bind
    private val models by activityViewModels<MainViewModels>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _bind = UserDashBoardBinding.inflate(layoutInflater)
        return bind?.root
    }

    private val shared by lazy {
        requireActivity().getSharedPreferences("user", Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(bind!!) {
            toPauseTheApp()


            models.getDetails()
            (shared.getString("name", "User") ?: "User").let {
                var store = ""
                it.forEachIndexed { index, c ->
                    store += if (index == 0) c.uppercaseChar() else c.lowercaseChar()
                }
                userWish.text = spanned("HI $store 😊 !!")
            }

            val adapter = ControllerAdapt(
                requireActivity() as AppCompatActivity,
                carrier = this@UserDashBoard
            )
            viewPager.adapter = adapter

            TabLayoutMediator(tabPoint, viewPager) { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "Chats"
                        tab.setIcon(R.drawable.group)
                    }

                    1 -> {
                        tab.text = "Activities"
                        tab.setIcon(R.drawable.baseline_view_compact_24)
                    }
                }
            }.attach()
            gravityPoint.setOnClickListener {
                findNavController().navigate(R.id.action_userDashBoard_to_chatBot)
            }
        }

    }

    override fun selectingFromChat(chatResponse: GroupsResponse.Groups) {
        val bundle = Bundle()
        bundle.putParcelable("Groups", chatResponse)

        findNavController().navigate(
            R.id.action_userDashBoard_to_chatArea,
            bundle
        )
    }

    override fun logout() {
        MaterialAlertDialogBuilder(
            ContextThemeWrapper(
                requireActivity(),
                R.style.Theme_PublicAnnouncementAndChat
            )
        ).apply {
            setTitle("Do you want Logout ?")
            setCancelable(false)
            setPositiveButton("Yes") { p, _ ->
                p.dismiss()

                requireActivity().let {
                    it.getSharedPreferences("user", Context.MODE_PRIVATE).edit().clear().apply()
                    it.finishAffinity()
                    it.startActivity(Intent(requireActivity().intent))
                }
            }
            setNegativeButton("No") { v, _ ->
                v.dismiss()
            }
        }.show()
    }
}