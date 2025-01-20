package chat.app.ui.users.gamers

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import chat.app.databinding.ChatBotBinding
import chat.app.ui.common.backPoint
import chat.app.ui.users.adapters.AiAdapter
import chat.app.ui.users.models.AiChat
import chat.app.ui.viewModels.MainViewModels
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class ChatBot : Fragment() {
    private var _bind: ChatBotBinding? = null
    val bind get() = _bind
    private val model by activityViewModels<MainViewModels>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _bind = ChatBotBinding.inflate(layoutInflater)
        return bind?.root
    }

    private val chat = arrayListOf<AiChat>()

    private val generate = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = "AIzaSyASEbtXBFhVIXDDL0rtaKOdx7ikrwkJ2_c"
    )

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(bind!!) {
            backPoint()


            val adapter = AiAdapter(requireActivity(), array = chat)
            cycle4.adapter = adapter
            adapter.notifyDataSetChanged()


            sendMessage2.setOnClickListener {

                val string = contentPoint2.text.toString()

                if (string.isEmpty()) {
                    model.toastPoint("Please enter the Text")
                    return@setOnClickListener
                }
                chat.add(AiChat(message = string, state = "You"))

                lifecycleScope.async {
                    async {
                        generate.generateContent(string)
                    }.await().let {
                        withContext(Main) {
                            contentPoint2.setText("")
                        }
                        chat.add(AiChat(message = it.text.toString(), state = "Ai"))
                    }
                }.start()


            }
        }
    }
}