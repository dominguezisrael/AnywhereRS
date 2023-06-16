package com.example.anywherers_codeexercise.model

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import com.example.anywherers_codeexercise.`interface`.IRVFilterableObject
import com.google.gson.annotations.SerializedName

/**
 * Data model to parse character information.
 */
data class CharacterModel(
    @SerializedName("Result") val description: String,
    @SerializedName("Text") val title: String,
    @SerializedName("Icon") val iconUrl: CharacterIcon
) : Parcelable, IRVFilterableObject {
    companion object CREATOR : Parcelable.Creator<CharacterModel> {
        override fun createFromParcel(parcel: Parcel): CharacterModel {
            return CharacterModel(parcel)
        }

        override fun newArray(size: Int): Array<CharacterModel?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    @Suppress("DEPRECATION")
    private constructor(parcelIn: Parcel) : this(
        description = parcelIn.readString() ?: "",
        title = parcelIn.readString() ?: "",
        iconUrl = if (Build.VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU) {
            parcelIn.readParcelable(
                CharacterIcon::class.java.classLoader,
                CharacterIcon::class.java
            )
        } else {
            parcelIn.readParcelable(CharacterIcon::class.java.classLoader)
        } as CharacterIcon,
    )

    override fun writeToParcel(parcelOut: Parcel, p1: Int) {
        parcelOut.writeString(description)
        parcelOut.writeString(title)
        parcelOut.writeParcelable(iconUrl, p1)
    }

    override fun getFilterConstraints(): List<String> {
        return listOf(description, title)
    }

    fun getName(): String {
        return title.split("-")[0].trim()
    }
}

/**
 * Data model to parse character's image url information.
 */
data class CharacterIcon(
    @SerializedName("URL") private val imageUrlSuffix: String
) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString() ?: "")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imageUrlSuffix)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CharacterIcon> {
        override fun createFromParcel(parcel: Parcel): CharacterIcon {
            return CharacterIcon(parcel)
        }

        override fun newArray(size: Int): Array<CharacterIcon?> {
            return arrayOfNulls(size)
        }
    }

    fun getUrl(): String {
        return "https://duckduckgo.com".plus(imageUrlSuffix)
    }
}