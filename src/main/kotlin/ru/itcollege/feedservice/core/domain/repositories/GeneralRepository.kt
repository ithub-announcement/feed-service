package ru.itcollege.feedservice.core.domain.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.itcollege.feedservice.core.domain.models.entities.General
import ru.itcollege.feedservice.core.domain.models.enums.GStatus
import java.util.*

@Repository
interface GeneralRepository : JpaRepository<General, UUID> {

  /**
   * ### findAllByStatus
   *
   * Получение всех записей по **status**.
   *
   * @param status
   * */

  fun findAllByStatus(status: GStatus): MutableList<General>

  /**
   * ### findByStatusAndUuid
   *
   * Получение записи по **status** и **uuid**.
   *
   * @param status
   * @param uuid
   * */

  fun findByStatusAndUuid(status: GStatus, uuid: UUID): General?

  /**
   * ### findByStatusAndAuthorId
   *
   * Получить список по **status** и **authorId**.
   *
   * @param status
   * @param authorId
   * */

  fun findByStatusAndAuthorId(status: GStatus, authorId: String): MutableList<General>
}