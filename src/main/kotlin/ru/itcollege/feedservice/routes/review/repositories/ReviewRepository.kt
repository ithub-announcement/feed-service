package ru.itcollege.feedservice.routes.review.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.itcollege.feedservice.routes.review.models.entities.Review
import java.util.*

@Repository
interface ReviewRepository : JpaRepository<Review, UUID>