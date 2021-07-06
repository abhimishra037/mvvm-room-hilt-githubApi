package c.com.githubrepolist.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface GithubRepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(githubRepoCacheEntity: GithubRepoCacheEntity): Long

    @Query ("SELECT * FROM githubRepo")
    suspend fun get(): List<GithubRepoCacheEntity>
}