package c.com.githubrepolist.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GithubRepoEntity(

    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("name")
    @Expose
    var name: String

   /* @SerializedName("description")
    @Expose
    var description: String*/
) {
}