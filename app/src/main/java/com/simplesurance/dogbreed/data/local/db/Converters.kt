package com.simplesurance.dogbreed.data.local.db

import androidx.room.TypeConverter
import com.simplesurance.dogbreed.data.local.db.Constants.COMMA

class Converters {
    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return stringListString.split(COMMA).map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = COMMA)
    }
}