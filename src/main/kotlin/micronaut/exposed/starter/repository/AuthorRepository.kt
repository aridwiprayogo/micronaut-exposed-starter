package micronaut.exposed.starter.repository

import io.micronaut.core.annotation.Blocking
import micronaut.exposed.starter.domain.AuthorEntity
import micronaut.exposed.starter.domain.Authors
import micronaut.exposed.starter.domain.AuthorDto
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*
import javax.inject.Singleton

@Singleton
@Blocking
class AuthorRepository(db: Database) {

    fun getAuthor(id: UUID) = transaction(statement = {
        Authors.select { Authors.id eq id }.asSequence().map {
            AuthorDto(it[Authors.name], it[Authors.version])
        }.toList()
    })

    fun save(authorDto: AuthorDto) = transaction {
        val authorEntity = AuthorEntity.new {
            name = authorDto.name
            version = authorDto.version
        }
        authorEntity.id.value
    }
}