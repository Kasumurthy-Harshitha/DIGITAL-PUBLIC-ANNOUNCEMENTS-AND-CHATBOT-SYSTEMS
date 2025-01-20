package chat.app.dataLayer.response

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class GroupsResponse (
    @SerializedName("error"   ) var error   : Boolean?        = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<Groups> = arrayListOf()
){
    data class Groups (
        @SerializedName("id"        ) var id        : String? = null,
        @SerializedName("image"     ) var image     : String? = null,
        @SerializedName("name"      ) var name      : String? = null,
        @SerializedName("descript"  ) var descript  : String? = null,
        @SerializedName("startedOn" ) var startedOn : String? = null
    ):Parcelable{
        constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(id)
            parcel.writeString(image)
            parcel.writeString(name)
            parcel.writeString(descript)
            parcel.writeString(startedOn)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Groups> {
            override fun createFromParcel(parcel: Parcel): Groups {
                return Groups(parcel)
            }

            override fun newArray(size: Int): Array<Groups?> {
                return arrayOfNulls(size)
            }
        }

    }
}
