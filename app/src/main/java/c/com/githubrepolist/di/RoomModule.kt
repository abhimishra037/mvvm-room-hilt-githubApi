package c.com.githubrepolist.di

import android.content.Context
import androidx.room.Room
import c.com.githubrepolist.room.GithubRepoDao
import c.com.githubrepolist.room.GithubRepoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideGithubRepoDb(@ApplicationContext context: Context): GithubRepoDatabase {
        return Room.databaseBuilder(
            context,
            GithubRepoDatabase::class.java,
            GithubRepoDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()

    }

    @Singleton
    @Provides
    fun provideGithubRepoDAO(githubRepoDatabase: GithubRepoDatabase): GithubRepoDao {
        return githubRepoDatabase.gitRepoDao()
    }

}