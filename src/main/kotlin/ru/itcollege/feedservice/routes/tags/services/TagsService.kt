package ru.itcollege.feedservice.routes.tags.services

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.itcollege.feedservice.core.config.MapperConfig
import ru.itcollege.feedservice.routes.tags.models.dto.TagPayload
import ru.itcollege.feedservice.routes.tags.models.entities.Tag
import ru.itcollege.feedservice.routes.tags.repositories.TagsRepository
import java.util.*

@Service
class TagsService(private var tagsRepository: TagsRepository, private var mapper: MapperConfig) {

  /**
   * ## findAll
   *
   * Получить список категорий.
   *
   * */

  fun findAll(): ResponseEntity<MutableList<Tag>> {
    val array = this.tagsRepository.findAll()
    return ResponseEntity.ok().body(array)
  }

  /**
   * ## findOneById
   *
   * Получить одну категорию по id.
   *
   * @param id
   * */

  fun findOneById(id: Long): ResponseEntity<Optional<Tag>> {
    val current = this.tagsRepository.findById(id)
    return ResponseEntity.ok().body(current)
  }

  /**
   * ## create
   *
   * Создать категорию.
   *
   * */

  fun create(payload: TagPayload): ResponseEntity<Tag> {
    val current = Optional.ofNullable(this.mapper.getMapper().map(payload, Tag::class.java)).get()
    return ResponseEntity.status(201).body(this.tagsRepository.save(current))
  }

  /**
   * ## delete
   *
   * Удалить категорию по id.
   *
   * @param id
   * */

  fun delete(id: Long) {
    this.tagsRepository.deleteById(id)
  }

  /**
   * ## changeProperty
   *
   * Метод чтобы изменить данные черновика.
   *
   * @param id
   * @param update
   * */

  private fun changeProperty(id: Long, update: (Tag) -> Unit): ResponseEntity<Tag> {
    val current = this.tagsRepository.findById(id).get().apply(update)
    return ResponseEntity.status(200).body(current)
  }

  /**
   * ## update
   *
   * Изменить категорию.
   *
   * @param id
   * @param payload
   * */

  fun update(id: Long, payload: TagPayload): ResponseEntity<Tag> {
    return this.changeProperty(id) {
      it.title = payload.title
      it.color = payload.color
    }
  }
}