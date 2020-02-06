package com.alamo.starwars.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FilmsResponse(
    @SerializedName("count")
    @Expose
    var count: Int? = null,

    @SerializedName("results")
    @Expose
    var results: List<Film>? = null
)
