package chat.app.dataLayer

import chat.app.dataLayer.response.ChatResponse
import chat.app.dataLayer.response.GroupsResponse
import chat.app.dataLayer.response.CommonResponse
import chat.app.dataLayer.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {

    @FormUrlEncoded
    @POST("users.php")
    suspend fun addUser(
        @Field("name") name: String,
        @Field("mobile") mobile: String,
        @Field("password") password: String,
    ): Response<CommonResponse>

    @GET("getData.php")
    suspend fun getLogin(
        @Query("mobile") mobile: String,
        @Query("condition") condition: String,
        @Query("password") password: String,
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("addGroup.php")
    suspend fun addGroup(
        @Field("image") image: String,
        @Field("name") name: String,
        @Field("descript") descript: String,
        @Field("startedOn") startedOn: String,
    ): Response<CommonResponse>

    @GET("getData.php")
    suspend fun getChats(
        @Query("condition")condition:String
    ):Response<GroupsResponse>

    @FormUrlEncoded
    @POST("addMessage.php")
    suspend fun addChatPoint(
        @Field("fromMail")fromMail:String,
        @Field("toMail")toMail:String,
        @Field("groupId")groupId:String,
        @Field("message")message:String,
        @Field("sentOn")sentOn:String,
        @Field("typeOf")typeOf:String,
        @Field("extraContent")extraContent:String
    ):Response<CommonResponse>


    @GET("getData.php")
    suspend fun getChatQuery(
        @Query("condition")condition:String,
        @Query("group")group:String
    ):Response<ChatResponse>
}
