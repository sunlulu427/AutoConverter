package com.mato.stg4cpp

import com.mato.stg4.annotation.STGClass
import com.mato.stg4.annotation.STGField

/**
 * @Author sunlulu.tomato
 * @Date 2023/12/29
 */
class SpinUser {
    @STGField
    val name: String = ""

    @STGField
    val gender: Int = 0

    @STGField
    val videos: List<Videos> = emptyList()

    @STGField
    val tags: List<Tag> = emptyList()

    @STGField
    val introduction: String = ""

    @STGField
    val friends: List<SpinUser> = emptyList()

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