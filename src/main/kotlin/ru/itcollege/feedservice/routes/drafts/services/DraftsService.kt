package ru.itcollege.feedservice.routes.drafts.services

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.itcollege.feedservice.core.api.authorization.AuthorizationGrpcClient
import ru.itcollege.feedservice.core.config.MapperConfig
import ru.itcollege.feedservice.core.domain.handlers.ResourceNotFoundException
import ru.itcollege.feedservice.core.domain.models.entities.General
import ru.itcollege.feedservice.core.domain.models.enums.GStatus
import ru.itcollege.feedservice.core.domain.repositories.GeneralRepository
import ru.itcollege.feedservice.routes.drafts.models.dto.DraftPayload
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*

@Service
class DraftsService(
  private var generalRepository: GeneralRepository,
  private var mapper: MapperConfig,
  private var authorizationGrpcClient: AuthorizationGrpcClient
) {

  /**
   * ## findAllByAuthorId
   *
   * Получить список черновиков по **authorId**
   *
   * */

  fun findAllByAuthorId(request: HttpServletRequest): ResponseEntity<MutableList<General>> {
    val authorId: String = this.authorizationGrpcClient.getUserByAccess(request.getHeader("Authorization")).toString()
    val current = this.generalRepository.findByStatusAndAuthorId(GStatus.DRAFT, authorId)
    return ResponseEntity.ok().body(current)
  }

  /**
   * ## findOneByUUID
   *
   * Получить черновик по **uuid**
   *
   * @param uuid
   * */

  fun findOneByUUID(uuid: String): ResponseEntity<General> {
    val current = this.generalRepository.findByStatusAndUuid(GStatus.DRAFT, UUID.fromString(uuid))
      ?: throw ResourceNotFoundException("Черновик не найден.")
    return ResponseEntity.ok().body(current)
  }

  /**
   * ## create
   *
   * Создание черновика.
   *
   * @param authorId
   * @param payload
   * */

  private fun create(authorId: String, payload: DraftPayload): ResponseEntity<General> {
    val current = Optional.ofNullable(this.mapper.getMapper().map(payload, General::class.java)).apply {
      this.get().date = ZonedDateTime.now(ZoneOffset.UTC)
      this.get().status = GStatus.DRAFT
      this.get().authorId = authorId
    }
    return ResponseEntity.status(201).body(this.generalRepository.save(current.get()))
  }

  /**
   * ## changeProperty
   *
   * Метод чтобы изменить данные черновика.
   *
   * @param uuid
   * @param update
   * */

  private fun changeProperty(uuid: String, update: (General?) -> Unit): ResponseEntity<General> {
    val current = this.generalRepository.findByStatusAndUuid(GStatus.DRAFT, UUID.fromString(uuid)).apply(update)
      ?: throw ResourceNotFoundException("Черновик не найден.")
    return ResponseEntity.status(200).body(current)
  }

  /**
   * ## update
   *
   * Обновление черновика.
   *
   * @param uuid
   * @param payload
   * */

  private fun update(uuid: String, payload: DraftPayload): ResponseEntity<General> {
    return this.changeProperty(uuid) {
      it?.title = payload.title
      it?.content = payload.content
      it?.date = ZonedDateTime.now(ZoneOffset.UTC)
    }
  }

  /**
   * ## save
   *
   * Сохранение черновика.
   *
   * @param uuid
   * @param payload
   * */

  fun save(uuid: String?, payload: DraftPayload, request: HttpServletRequest): ResponseEntity<General> {
    val authorId: String = this.authorizationGrpcClient.getUserByAccess(request.getHeader("Authorization")).toString()
    return uuid?.let { this.update(it, payload) } ?: this.create(authorId, payload)
  }

  /**
   * ## archive
   *
   * Архивировать черновик.
   *
   * @param uuid
   * */

  fun archive(uuid: String): ResponseEntity<General> {
    return this.changeProperty(uuid) {
      it?.status = GStatus.ARCHIVE
    }
  }

}