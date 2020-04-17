package micronaut.exposed.starter.controller

import io.micronaut.http.annotation.*
import micronaut.exposed.starter.domain.AuthorDto
import micronaut.exposed.starter.repository.AuthorRepository
import java.util.*

@Controller("/author")
class AuthorController(private val _repository: AuthorRepository) {

    @Get("/{id}")
    fun author(@QueryValue id: UUID): List<AuthorDto> = _repository.getAuthor(id)

    @Post("/")
    fun newAuthor(@Body author: AuthorDto): UUID = _repository.save(author)
}
