package c.com.githubrepolist.repository

import android.util.Log
import c.com.githubrepolist.model.GithubRepo
import c.com.githubrepolist.retrofit.GithubRepoRetrofit
import c.com.githubrepolist.retrofit.NetworkMapper
import c.com.githubrepolist.room.CacheMapper
import c.com.githubrepolist.room.GithubRepoDao
import c.com.githubrepolist.utility.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val githubRepoDao: GithubRepoDao,
    private val githubRepoRetrofit: GithubRepoRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {

    suspend fun getGitRepo(): Flow<DataState<List<GithubRepo>>> = flow {
        emit(DataState.Loading)
        delay(100)
        try {
            if (githubRepoDao.get().isEmpty()) {
                Log.d("repodddddd","yes"+githubRepoDao.get().size)
                val networkGithubRepo = githubRepoRetrofit.get()
                val githubRepos = networkMapper.mapFromEntityList(networkGithubRepo)
                for (githubRepo in githubRepos) {
                    githubRepoDao.insert(cacheMapper.mapToEntity(githubRepo))
                }
                val cacheGithubRepo = githubRepoDao.get()
                emit(DataState.Success(cacheMapper.mapFromEntityList(cacheGithubRepo)))
                Log.d("repoddddd","no00"+githubRepoDao.get().toString())

            } else {
                Log.d("repoddddd","no"+githubRepoDao.get().toString())

                val cacheGithubRepo = githubRepoDao.get()
                emit(DataState.Success(cacheMapper.mapFromEntityList(cacheGithubRepo)))
            }


        } catch (e: Exception) {
            emit(DataState.Error(e))

        }
    }

}