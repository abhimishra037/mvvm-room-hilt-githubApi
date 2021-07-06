package c.com.githubrepolist.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GithubRepoCacheEntity::class],version = 1,exportSchema = false)
abstract class GithubRepoDatabase: RoomDatabase(){
    abstract fun gitRepoDao() : GithubRepoDao

    companion object{
        val DATABASE_NAME: String = "gitRepo_db"
    }
}