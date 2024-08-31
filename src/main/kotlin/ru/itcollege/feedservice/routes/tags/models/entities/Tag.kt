package ru.itcollege.feedservice.routes.tags.models.entities

import jakarta.persistence.*

@Entity(name = "tags")
class Tag {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false, updatable = false)
  var id: Long = 0

  @Column(unique = true, nullable = false, updatable = true)
  lateinit var title: String

  @Column(unique = false, nullable = false, updatable = true)
  lateinit var color: String
}