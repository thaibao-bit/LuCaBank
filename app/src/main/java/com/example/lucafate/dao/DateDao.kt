package com.example.lucafate.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lucafate.entities.Dates

@Dao
interface DateDao {
    @get:Query("SELECT * FROM dates ORDER BY id DESC")
    val  allDates: List<Dates>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDate(dates: Dates)
}