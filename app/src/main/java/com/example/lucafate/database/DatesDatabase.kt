package com.example.lucafate.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lucafate.dao.DateDao
import com.example.lucafate.entities.Dates

@Database(entities = [Dates::class], version = 1, exportSchema = false)
abstract class DatesDatabase : RoomDatabase() {
    companion object{
        var datesDatabase: DatesDatabase? = null

        @Synchronized
        fun getDataBase(context: Context): DatesDatabase{
            if(datesDatabase != null){
                datesDatabase = Room.databaseBuilder(
                    context,
                    DatesDatabase::class.java,
                    "date.db"
                ).build()
            }
            return  datesDatabase!!
        }
    }

    abstract fun dateDao():DateDao

}