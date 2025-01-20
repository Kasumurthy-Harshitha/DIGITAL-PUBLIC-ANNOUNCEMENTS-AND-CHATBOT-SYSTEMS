package chat.app.ui.admin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import chat.app.R
import chat.app.databinding.AddGroupBinding
import chat.app.ui.common.backPoint
import chat.app.ui.viewModels.MainViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AddGroup : Fragment() {
    private var _bind: AddGroupBinding? = null
    private val bind get() = _bind
    private val models by activityViewModels<MainViewModels>()

    private var image: Uri? = null

    private val imagePoint = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        activityResult.data?.data?.let {
            image = it
            bind?.selectedImage?.setImageURI(it)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _bind = AddGroupBinding.inflate(layoutInflater)
        return bind?.root
    }

    @SuppressLint("Recycle")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(bind!!) {
            val nav = findNavController()
            models.toast.onEach {
                if (it == "Success") {
                    nav.navigateUp()
                }
            }.launchIn(lifecycleScope)


            backPoint()
            cardView4.setOnClickListener {
                imagePoint.launch(Intent(Intent.ACTION_GET_CONTENT).setType("image/*"))
            }
            createGroup.setOnClickListener {
                val gName = gName.text.toString().trim()
                val groupDes = groupDes.text.toString().trim()
                if (gName.isEmpty()) {
                    models.toastPoint("Please enter the Group Name")
                } else if (groupDes.isEmpty()) {
                    models.toastPoint("Please enter the Group Description")
                } else if (groupDes.length <= 30) {
                    models.toastPoint("Please enter the Group Description\n more than  30 letters")
                } else if (image == null) {
                    models.toastPoint("Please select image From your Gallery")
                } else {
                    val imageByte =
                        requireContext().contentResolver.openInputStream(image!!)?.readBytes()
                    if (imageByte == null) {
                        models.toastPoint("Please select other image")
                    } else {
                        val image = Base64.encodeToString(imageByte, Base64.NO_WRAP)
                        models.addGroup(gName = gName, groupDes = groupDes, image = image)
                    }
                }
            }


        }
    }

}
