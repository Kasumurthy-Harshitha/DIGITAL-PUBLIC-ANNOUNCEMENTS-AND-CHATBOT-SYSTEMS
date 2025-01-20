package chat.app.dataLayer.response

import com.google.gson.annotations.SerializedName

data class CommonResponse (

    @SerializedName("error"   ) var error   : Boolean? = null,
    @SerializedName("message" ) var message : String?  = null
)


