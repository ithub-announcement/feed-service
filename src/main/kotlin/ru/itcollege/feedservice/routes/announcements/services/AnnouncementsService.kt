package ru.itcollege.feedservice.routes.announcements.services

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

  fun findAll(): MutableList<General> {
    return this.generalRepository.findAllByStatus(GStatus.PUBLIC)
  }

  /**
   * ## findOneByUUID
   *
   * Получить публикацию по **uuid**.
   *
   * @param uuid
   * */

  fun findOneByUUID(uuid: String): General? {
    return this.generalRepository.findByStatusAndUuid(GStatus.PUBLIC, UUID.fromString(uuid))
  }

}