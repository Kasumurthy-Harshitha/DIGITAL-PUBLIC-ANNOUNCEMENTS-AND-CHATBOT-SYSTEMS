package chat.app.ui.users.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import chat.app.databinding.ChatPointBinding
import chat.app.ui.common.spanned
import chat.app.ui.users.models.AiChat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class AiAdapter(
    private val context: Context,
    private val array: ArrayList<AiChat>,
) : RecyclerView.Adapter<AiAdapter.ViewHolderPoint>() {
    class ViewHolderPoint(
        val chat: ChatPointBinding,
    ) : RecyclerView.ViewHolder(chat.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolderPoint(
        ChatPointBinding.inflate(LayoutInflater.from(context), parent, false)
    )


    override fun getItemCount() = array.size

    override fun onBindViewHolder(holder: ViewHolderPoint, position: Int) {
        with(holder.chat) {
            array[position].let { chat ->
                if (chat.state == "You") {
                    chatRight.isVisible = true
                    chatLeft.isVisible = false
                    contentRight.text=chat.message
                } else {
                    chatLeft.isVisible = true
                    chatRight.isVisible = false
                    contentLeft.text= chat.message
                    runnableSpam(contentLeft)


                }
                chatImageRight.isVisible = false
                chatImageLeft.isVisible = false


            }
        }
    }

    private fun runnableSpam(contentRight: TextView) {
        val string = contentRight.text
        contentRight.text = ""
        CoroutineScope(IO).async {
            for (i in string.indices) {
                delay(200)
                withContext(Main){
                    contentRight.append(string[i].toString())
                }
            }
        }.start()


    }
}