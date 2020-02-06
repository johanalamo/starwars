package com.alamo.starwars.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("gender")
    @Expose
    var gender: String? = null,

    @SerializedName("birth_year")
    @Expose
    var birthYear: String? = null
)
