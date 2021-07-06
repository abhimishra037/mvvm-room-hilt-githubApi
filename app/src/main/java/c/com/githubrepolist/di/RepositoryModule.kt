package c.com.githubrepolist.di

import c.com.githubrepolist.repository.MainRepository
import c.com.githubrepolist.retrofit.GithubRepoRetrofit
import c.com.githubrepolist.retrofit.NetworkMapper
import c.com.githubrepolist.room.CacheMapper
import c.com.githubrepolist.room.GithubRepoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        githubRepoDao: GithubRepoDao,
        githubRepoRetrofit: GithubRepoRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository{
        return MainRepository(githubRepoDao,githubRepoRetrofit,cacheMapper,networkMapper)
    }
}