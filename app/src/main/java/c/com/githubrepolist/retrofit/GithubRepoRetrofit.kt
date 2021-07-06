package c.com.githubrepolist.retrofit

import retrofit2.http.GET

interface GithubRepoRetrofit {

    @GET("users/bibeva/repos")
    suspend fun get(): List<GithubRepoEntity>
}