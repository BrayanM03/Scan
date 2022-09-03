package com.example.scan

import android.os.Parcel
import android.os.Parcelable
import android.text.Editable
import com.google.gson.annotations.SerializedName

data class Credenciales(
    @SerializedName("user") val user: String,
    @SerializedName("password") val password: String)


//A partir de funciono

data class ResponseItem(
    @SerializedName("mensaje" ) var mensaje : String        ,
    @SerializedName("data"    ) var data    : ArrayList<Data> = arrayListOf(),
    @SerializedName("estatus" ) var estatus : Boolean?        = null
    /*val mensaje: String?,
    val estatus: Boolean?,
    val data: List<UserData>?*/
)



data class  Data(
    @SerializedName("id"       ) var id       : String? = null,
    @SerializedName("nombre"   ) var nombre   : String? = null,
    @SerializedName("apellido" ) var apellido : String? = null,
    @SerializedName("usuario"  ) var usuario  : String? = null,
    @SerializedName("rol"      ) var rol      : String? = null,
    @SerializedName("sucursal" ) var sucursal : String? = null
    /*val id: String,
    val nombre: String?,
    val apellido: String?,
    val usuario: String?,
    val rol: String?,
    val sucursal: String?*/
)


