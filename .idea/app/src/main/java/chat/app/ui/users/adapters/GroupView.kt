package chat.app.ui.users.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import chat.app.R
import chat.app.dataLayer.response.GroupsResponse
import chat.app.databinding.GroupsCardBinding
import coil.load

class GroupView(
    private val context: Context, private val array: ArrayList<GroupsResponse.Groups>,
    val navigate: (GroupsResponse.Groups) -> Unit,
) :
    RecyclerView.Adapter<GroupView.ViewPort>() {

    class ViewPort(val item: GroupsCardBinding) : RecyclerView.ViewHolder(item.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewPort(
        GroupsCardBinding.inflate(LayoutInflater.from(context), parent, false)
    )

    override fun getItemCount() = array.size

    override fun onBindViewHolder(holder: ViewPort, position: Int) {
        with(holder.item) {
            array[position].let {
                mainImage.load(it.image ?: R.drawable.group)
                titleText.text = it.name
                descriptions.text = it.descript
                root.setOnClickListener {_->
                navigate.invoke(it)
                }
            }
        }
    }
}