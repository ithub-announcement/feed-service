package ru.itcollege.feedservice.routes.tags.models.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "tags")
class Tag {
  @Id
  @Column(unique = true, nullable = false, updatable = false)
  var id: Long = 0

  @Column(unique = true, nullable = false, updatable = true)
  lateinit var title: String

  @Column(unique = false, nullable = false, updatable = true)
  lateinit var color: String
}