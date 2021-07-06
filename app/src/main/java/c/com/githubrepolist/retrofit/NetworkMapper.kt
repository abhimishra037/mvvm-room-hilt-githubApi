package c.com.githubrepolist.retrofit

import c.com.githubrepolist.model.GithubRepo
import c.com.githubrepolist.utility.EntityMapper
import javax.inject.Inject

class NetworkMapper @Inject constructor() : EntityMapper<GithubRepoEntity, GithubRepo> {
    override fun mapFromEntity(entity: GithubRepoEntity): GithubRepo {
        return GithubRepo(id = entity.id, name = entity.name)
    }

    override fun mapToEntity(domainModel: GithubRepo): GithubRepoEntity {
        return GithubRepoEntity(
            id = domainModel.id,
            name = domainModel.name
           // description = domainModel.description
        )
    }

    fun mapFromEntityList(entities: List<GithubRepoEntity>): List<GithubRepo> {
        return entities.map { mapFromEntity(it) }
    }
}