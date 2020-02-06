package com.alamo.starwars.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Film(
    @SerializedName("episode_id")
    @Expose
    var episodeId: String? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("director")
    @Expose
    var director: String? = null,

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null,

    @SerializedName("characters")
    @Expose
    var characters: List<String>? = null
) : Parcelable {
    constructor (source: Parcel) : this(
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.readString()!!,
        source.createStringArrayList()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(episodeId)
        dest.writeString(title)
        dest.writeString(director)
        dest.writeString(releaseDate)
        dest.writeStringList(characters)
    }

    companion object CREATOR : Parcelable.Creator<Film> {
        override fun createFromParcel(source: Parcel): Film = Film(source)
        override fun newArray(size: Int): Array<Film?> = arrayOfNulls(size)
    }
}
