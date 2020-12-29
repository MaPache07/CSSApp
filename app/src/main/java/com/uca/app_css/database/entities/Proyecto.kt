package com.uca.app_css.database.entities

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "proyecto")
data class Proyecto(
    @PrimaryKey
    val idProyecto: Int = -1,
    val estado: Int = -1,
    val contraparte: String? = "",
    val cupos: Int = -1,
    val descripcion: String? = "",
    val encargado: String? = "",
    val fecha_inicio: String? = "",
    val fecha_fin: String? = "",
    val horario: String? = "",
    val nombre: String? = "",
    val tipo_horas: String? = "",
    val modifiedAt: String? = "",
    val modifiedBy: String? = "",
    val createdAt: String? = ""
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(idProyecto)
        dest.writeInt(estado)
        dest.writeString(contraparte)
        dest.writeInt(cupos)
        dest.writeString(descripcion)
        dest.writeString(encargado)
        dest.writeString(fecha_inicio)
        dest.writeString(fecha_fin)
        dest.writeString(horario)
        dest.writeString(nombre)
        dest.writeString(tipo_horas)
        dest.writeString(modifiedAt)
        dest.writeString(modifiedBy)
        dest.writeString(createdAt)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Proyecto>{
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Proyecto {
            return Proyecto(parcel)
        }

        override fun newArray(size: Int): Array<Proyecto?> {
            return arrayOfNulls(size)
        }
    }
}