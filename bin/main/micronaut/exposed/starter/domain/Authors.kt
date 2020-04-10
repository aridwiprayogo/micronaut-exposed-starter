package micronaut.exposed.starter.domain

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.util.*

object Authors: UUIDTable(name = "author", columnName = "author_id") {
    var name = text("name")
    var createdAt = datetime("created_at")
    var modifiedAt = datetime("updated_at")
    var version = integer("version")
}

class AuthorEntity(_id: EntityID<UUID>): UUIDEntity(_id) {
    companion object: UUIDEntityClass<AuthorEntity>(Authors)
    var name by Authors.name
    var version by Authors.version
}

data class AuthorDto(
        val name: String,
        val version: Int
)