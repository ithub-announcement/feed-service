package ru.itcollege.feedservice.routes.tags.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.itcollege.feedservice.routes.tags.models.entities.Tag

@Repository
interface TagsRepository : JpaRepository<Tag, Long>