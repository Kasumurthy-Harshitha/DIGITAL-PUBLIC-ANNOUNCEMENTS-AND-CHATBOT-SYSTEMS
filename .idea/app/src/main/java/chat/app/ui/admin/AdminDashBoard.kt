package chat.app.ui.admin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import chat.app.R
import chat.app.dataLayer.response.LoginResponse
import chat.app.databinding.AdminDashBoardBinding
import chat.app.ui.viewModels.MainViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AdminDashBoard : Fragment() {
    private var _bind: AdminDashBoardBinding? = null
    private val bind get() = _bind

    private val models by activityViewModels<MainViewModels>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _bind = AdminDashBoardBinding.inflate(layoutInflater)
        return bind?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(bind!!) {
            val nav = findNavController()
            cardView2.setOnClickListener {
                nav.navigate(R.id.action_adminDashBoard_to_addGroup)
            }
            logoutPoint.setOnClickListener {
                logout()
            }
            adminChatArea.setOnClickListener {
                nav.navigate(R.id.action_adminDashBoard_to_viewAllGroups)
            }
        }
    }

    private fun logout() {
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