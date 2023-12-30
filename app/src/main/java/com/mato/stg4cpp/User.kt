package com.mato.stg4cpp

import com.mato.stg4.annotation.STGClass
import com.mato.stg4.annotation.STGField

@STGClass
class User {
    @STGField
    val name: String = ""

    @STGField
    val gender: Int = 0

    @STGField
    val videos: List<Videos> = emptyList()

    @STGField
    val tags: List<Tag> = emptyList()

    @STGField
    val online: Boolean = false

    @STGField
    val id: Long = 0L

    @STGField
    val weight: Float = 0.0f

    @STGField
    var money: Double? = 0.0

    @STGClass
    class Videos(
        @STGField val title: String = "",
        @STGField val poster: String = "",
        @STGField val likesCount: Int = 0,
        @STGField val tags: List<Tag> = emptyList()
    )

    @STGClass
    class Tag(
        @STGField val name: String = "",
        @STGField val expire: Int = 0
    )
}