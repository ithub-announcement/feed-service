package ru.itcollege.feedservice.routes.announcements.services

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.itcollege.feedservice.core.domain.models.entities.General
import ru.itcollege.feedservice.core.domain.models.enums.GStatus
import ru.itcollege.feedservice.core.domain.repositories.GeneralRepository
import java.util.*

@Service
class AnnouncementsService(private var generalRepository: GeneralRepository) {

  /**
   * ## findAll
   *
   * Получить список публикаций.
   *
   * */

  fun findAll(): ResponseEntity<MutableList<General>> {
    val array = this.generalRepository.findAllByStatus(GStatus.PUBLIC)
    return ResponseEntity.ok().body(array)
  }

  /**
   * ## findOneByUUID
   *
   * Получить публикацию по **uuid**.
   *
   * @param uuid
   * */

  fun findOneByUUID(uuid: String): ResponseEntity<General> {
    val current = this.generalRepository.findByStatusAndUuid(GStatus.PUBLIC, UUID.fromString(uuid))
    return ResponseEntity.ok().body(current)
  }

}