package micronaut.exposed.starter.domain

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.Instant
import java.util.*

object Authors: UUIDTable(name = "author", columnName = "author_id") {
    var name = text("name")
    var createdAt = datetime("created_at")
    var modifiedAt = datetime("updated_at").nullable()
    var version = integer("version")
}

class AuthorEntity(_id: EntityID<UUID>): UUIDEntity(_id) {
    companion object: UUIDEntityClass<AuthorEntity>(Authors)
    var name by Authors.name
    var version by Authors.version
    var createdAt by Authors.createdAt
    var modifiedAt by Authors.modifiedAt

    fun toAuthor()=Author(
            name = this.name,
            version = this.version
    )
}
data class Author(
        val name: String,
        val version: Int,
        val createdAt: Instant = Instant.now(),
        val modifiedAt: Instant = Instant.now()
)
data class AuthorDto(
        val name: String,
        val version: Int
)