package com.example.chalenge4

import com.example.chalenge4.articletable.Article
import com.example.chalenge4.articletable.ArticleDao
import com.example.chalenge4.usertable.User
import com.example.chalenge4.usertable.UserDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        Article::class,
        User::class
    ],
    version = 1
)
abstract class ArticleManagementDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao
    abstract fun articleDao() : ArticleDao

    companion object{
        // Instance --------------------------------------------------------------------------------
        private var INSTANCE : ArticleManagementDatabase? = null
        fun getInstance(context : Context):ArticleManagementDatabase? {
            if (INSTANCE == null){
                synchronized(ArticleManagementDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        ArticleManagementDatabase::class.java,"ArticleManagement.db").build()
                }
            }
            return INSTANCE
        }

        // Destroy Instance ------------------------------------------------------------------------
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}