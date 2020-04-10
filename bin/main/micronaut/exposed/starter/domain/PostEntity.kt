package micronaut.exposed.starter.domain

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.`java-time`.datetime

object PostEntity: UUIDTable(name = "posts", columnName = "idPost") {
    val idAuthor = (uuid("author_id").references(Authors.id))
    val title = varchar(name = "title", length = 255)
    val content = text("content")
    val createdAt = datetime("created_at")
    val modifiedAt = datetime("modified_at")
}