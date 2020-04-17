package micronaut.exposed.starter.repository

import io.micronaut.core.annotation.Blocking
import micronaut.exposed.starter.domain.AuthorDto
import micronaut.exposed.starter.domain.AuthorEntity
import micronaut.exposed.starter.domain.Authors
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import java.time.LocalDateTime
import java.util.*
import javax.inject.Singleton

@Singleton
@Blocking
class AuthorRepository(private val db: Database) {

    suspend fun getAuthorAsync(id: UUID) = suspendedTransactionAsync(db = db) {
        AuthorEntity.find { Authors.id eq id }.firstOrNull()?.toAuthor()
    }

    suspend fun update(id: UUID, authorDto: AuthorDto) = newSuspendedTransaction(db=db){
        val authorEntity = AuthorEntity.find { Authors.id eq id }.firstOrNull()
        authorEntity?.name = authorDto.name
        authorEntity?.version = authorDto.version
        authorEntity?.modifiedAt = LocalDateTime.now()
    }

    suspend fun saveAsync(authorDto: AuthorDto) = suspendedTransactionAsync(db = db) {
        val authorResult = AuthorEntity.new {
            name = authorDto.name
            version = authorDto.version
            createdAt = LocalDateTime.now()
            modifiedAt = null
        }
        authorResult.id.value
    }

    suspend fun deleteAuthor(id: UUID)= newSuspendedTransaction(db=db) {
        AuthorEntity.find { Authors.id eq id }.firstOrNull()?.delete()
    }
}
