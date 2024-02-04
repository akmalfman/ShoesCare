package com.example.shoescare.model
import android.os.Parcel
import android.os.Parcelable

data class History(
    val uid: String? = null,
    val name: String? = null,
    val phone: String? = null,
    val quantity: String? = null,
    val total: String? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uid)
        parcel.writeString(name)
        parcel.writeString(phone)
        parcel.writeString(quantity)
        parcel.writeString(total)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<History> {
        override fun createFromParcel(parcel: Parcel): History {
            return History(parcel)
        }

        override fun newArray(size: Int): Array<History?> {
            return arrayOfNulls(size)
        }
    }
}
