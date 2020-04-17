package micronaut.exposed.starter.domain

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

object Posts: UUIDTable(name = "posts", columnName = "idPost") {
    var idAuthor: Column<UUID> = (uuid("author_id").references(Authors.id))
    var title: Column<String> = varchar(name = "title", length = 255)
    var content: Column<String> = text("content")
    var createdAt: Column<LocalDateTime> = datetime("created_at")
    var modifiedAt: Column<LocalDateTime?> = datetime("modified_at").nullable()
}

class PostEntity(id: EntityID<UUID>): UUIDEntity(id){
    companion object : UUIDEntityClass<PostEntity>(Posts)
    var idPost by Posts.id
    var idAuthor by Posts.idAuthor
    var title by Posts.title
    var content by Posts.content
    var createdAt by Posts.createdAt
    var modifiedAt by Posts.modifiedAt
}

data class Post(
        val idAuthor: UUID,
        val title: String,
        val content: String,
        val createdAt: Instant,
        val modifiedAt: Instant
)