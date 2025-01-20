package chat.app.dataLayer.response

import com.google.gson.annotations.SerializedName

data class ChatResponse(
    @SerializedName("error"   ) var error   : Boolean?        = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<Chat> = arrayListOf()
){
    data class Chat(  @SerializedName("id"           ) var id           : String? = null,
                      @SerializedName("fromMail"     ) var fromMail     : String? = null,
                      @SerializedName("toMail"       ) var toMail       : String? = null,
                      @SerializedName("groupId"      ) var groupId      : String? = null,
                      @SerializedName("message"      ) var message      : String? = null,
                      @SerializedName("sentOn"       ) var sentOn       : String? = null,
                      @SerializedName("typeOf"       ) var typeOf       : String? = null,
                      @SerializedName("extraContent" ) var extraContent : String? = null,
                      @SerializedName("name"         ) var name         : String? = null
    )
}