package ru.itcollege.feedservice.routes.tags.services

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

  fun findAll(): MutableList<Tag> {
    return this.tagsRepository.findAll()
  }

  /**
   * ## findOneById
   *
   * Получить одну категорию по id.
   *
   * @param id
   * */

  fun findOneById(id: Long): Optional<Tag> {
    return this.tagsRepository.findById(id)
  }

  /**
   * ## create
   *
   * Создать категорию.
   *
   * */

  fun create(payload: TagPayload): Tag {
    val current = Optional.ofNullable(this.mapper.getMapper().map(payload, Tag::class.java)).get()
    return this.tagsRepository.save(current)
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
   * ## update
   *
   * Изменить категорию.
   *
   * @param id
   * @param payload
   * */

  fun update(id: Long, payload: TagPayload): Tag {
    val current = this.tagsRepository.findById(id).get().apply {
      this.title = payload.title
      this.color = payload.color
    }
    return this.tagsRepository.save(current)
  }
}