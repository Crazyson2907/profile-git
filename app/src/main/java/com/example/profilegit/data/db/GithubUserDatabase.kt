package com.example.profilegit.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.profilegit.data.model.Repository
import com.example.profilegit.data.model.User

@Database(
    entities = [User::class, Repository::class],
    version = 1
)
abstract class GithubUserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun repoDao(): RepoDao

    companion object {
        private const val DATABASE_NAME = "notes_db"
        private var INSTANCE: GithubUserDatabase? = null

        fun getInstance(context: Context): GithubUserDatabase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GithubUserDatabase::class.java,
                        DATABASE_NAME
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}