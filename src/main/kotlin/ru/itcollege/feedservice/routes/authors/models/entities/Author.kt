package ru.itcollege.feedservice.routes.authors.models.entities

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.PrimaryKeyJoinColumn

@Entity(name = "authors")
class Author {
  @Id
  @PrimaryKeyJoinColumn()
  lateinit var uid: String

  @ManyToMany
  lateinit var followers: List<Author>
}