package ru.itcollege.feedservice.core.domain.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ru.itcollege.feedservice.core.domain.models.entities.General
import ru.itcollege.feedservice.core.domain.models.enums.GStatus
import java.util.UUID

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
   * */

  fun findByStatusAndUuid(status: GStatus, uuid: UUID): General
}