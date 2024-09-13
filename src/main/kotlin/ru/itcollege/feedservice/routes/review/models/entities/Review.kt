package ru.itcollege.feedservice.routes.review.models.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.UuidGenerator
import ru.itcollege.feedservice.routes.review.models.enums.RStatus
import java.time.ZonedDateTime
import java.util.*

@Entity(name = "review")
class Review {
  @Id
  @GeneratedValue(generator = "UUID")
  @UuidGenerator
  @Column(unique = true, nullable = false, updatable = false)
  lateinit var uuid: UUID

  @Column(unique = true, nullable = false, updatable = false)
  lateinit var uuidDraft: UUID

  @Column(unique = false, nullable = false, updatable = true)
  lateinit var date: ZonedDateTime

  @Column(unique = false, nullable = false, updatable = false)
  lateinit var authorId: String

  @Column(unique = false, nullable = false, updatable = true)
  lateinit var status: RStatus
}