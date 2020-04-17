package micronaut.exposed.starter.controller

import io.micronaut.http.annotation.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import micronaut.exposed.starter.domain.Author
import micronaut.exposed.starter.domain.AuthorDto
import micronaut.exposed.starter.repository.AuthorRepository
import java.util.*

@ExperimentalCoroutinesApi
@Controller("/author")
class AuthorController(private val _repository: AuthorRepository) {

    @Get("/{id}")
    fun author(@QueryValue id: UUID): Author? = runBlocking(Dispatchers.IO){ _repository.getAuthorAsync(id).await() }

    @Post("/")
    fun newAuthor(@Body author: AuthorDto): UUID = runBlocking(Dispatchers.IO){ _repository.saveAsync(author).await() }

    @Put("/{id}")
    fun updateAuthor(@QueryValue id: UUID, @Body author: AuthorDto) = runBlocking(Dispatchers.IO){ _repository.update(id, author) }

    @Delete("/{id}")
    fun deleteAuthor(@QueryValue id: UUID) = runBlocking(Dispatchers.IO){ _repository.deleteAuthor(id) }
}
