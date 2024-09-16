package com.example.room.model



import android.location.Address
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
//@Parcelize
@Entity(tableName = "user_data")
data class User (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @NotNull
    val fname:String,
    val lname:String,
    val age:Int,
   // @Embedded
   // val address:com.example.room.model.Address
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readInt(),

    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(fname)
        parcel.writeString(lname)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}

data class Address(
        val streetName:String?,
        val streetNumber:Int
)