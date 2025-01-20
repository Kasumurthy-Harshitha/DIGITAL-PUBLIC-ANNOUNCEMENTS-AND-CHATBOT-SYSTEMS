package chat.app.dataLayer.response

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("error"   ) var error   : Boolean?        = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<Users> = arrayListOf()
){
    data class Users (
        @SerializedName("id"       ) var id       : String? = null,
        @SerializedName("name"     ) var name     : String? = null,
        @SerializedName("mobile"   ) var mobile   : String? = null,
        @SerializedName("password" ) var password : String? = null
    )
}
