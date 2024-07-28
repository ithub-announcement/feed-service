package ru.itcollege.feedservice.core.domain.models.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import org.hibernate.annotations.UuidGenerator
import ru.itcollege.feedservice.core.domain.models.enums.GStatus
import java.time.ZonedDateTime
import java.util.UUID

@Entity(name = "general")
class General {
  @Id
  @GeneratedValue(generator = "UUID")
  @UuidGenerator
  @Column(unique = true, nullable = false, updatable = false)
  lateinit var uuid: UUID

  @Column(unique = false, nullable = false, updatable = true)
  lateinit var title: String

  @Column(unique = false, nullable = false, updatable = true, columnDefinition = "TEXT")
  lateinit var content: String

  @Column(unique = false, nullable = false, updatable = false)
  lateinit var authorId: String

  @Column(unique = false, nullable = false, updatable = true)
  lateinit var date: ZonedDateTime

  @JsonIgnore
  @Column(unique = false, nullable = false, updatable = true)
  lateinit var status: GStatus
}