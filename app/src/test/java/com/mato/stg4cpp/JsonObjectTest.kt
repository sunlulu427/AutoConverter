package com.mato.stg4cpp

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class JsonObjectTest {
    @Test
    fun testJsonPut() {
        val restaurant = Restaurant(
            score = 4.3f,
            comments = 1000,
            foods = listOf("A", "B", "C")
        )
        val json = restaurant.toJSONObject().getOrThrow()
        val location = json.get("location")
        assertTrue(location is String)
        assertEquals(location, "122")

        val foods = json.get("foods")
//        assertTrue(foods is JSONArray)
    }
}