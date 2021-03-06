package c.com.githubrepolist.room

import c.com.githubrepolist.model.GithubRepo
import c.com.githubrepolist.utility.EntityMapper
import javax.inject.Inject

class CacheMapper @Inject constructor() : EntityMapper<GithubRepoCacheEntity, GithubRepo> {
    override fun mapFromEntity(entity: GithubRepoCacheEntity): GithubRepo {

        return GithubRepo(id = entity.id, name = entity.name, language = entity.language)
    }

    override fun mapToEntity(domainModel: GithubRepo): GithubRepoCacheEntity {
        return GithubRepoCacheEntity(
            id = domainModel.id,
            name = domainModel.name,
            language = domainModel.language
        )
    }

    fun mapFromEntityList(entities: List<GithubRepoCacheEntity>): List<GithubRepo> {
        return entities.map { mapFromEntity(it) }
    }
}