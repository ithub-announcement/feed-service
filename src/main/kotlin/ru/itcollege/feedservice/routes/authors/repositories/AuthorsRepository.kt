package ru.itcollege.feedservice.routes.authors.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.itcollege.feedservice.routes.authors.models.entities.Author

@Repository
interface AuthorsRepository : JpaRepository<Author, String> {
}