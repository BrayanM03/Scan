package com.example.scan.model

import android.text.Editable
import com.example.scan.ResponseItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService {
   // @GET
   // fun getUserData(@Url url:String):Response<userResponse>
    //@FormUrlEncoded

    @POST("API_PSC/validar_usuario.php")
    @FormUrlEncoded
    fun validarUsuario(@Field("user") user:String, @Field("password") password: String): Call <ResponseItem>


    @POST("API_PSC/main.php")
    fun validarSesion(): Call <ResponseItem>

    @POST("API_PSC/obtener_usuario.php")
    @FormUrlEncoded
    suspend fun obtenerUsuario(@Field("user") user: String): Response<ResponseItem>



}

