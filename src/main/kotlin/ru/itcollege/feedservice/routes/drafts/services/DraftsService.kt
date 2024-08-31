package ru.itcollege.feedservice.routes.drafts.services

import jakarta.servlet.http.HttpServletRequest
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
   * */

  fun findAllByAuthorId(request: HttpServletRequest): MutableList<General> {
    val authorId: String = this.authorizationGrpcClient.getUserByAccess(request.getHeader("Authorization")).toString()
    return this.generalRepository.findByStatusAndAuthorId(GStatus.DRAFT, authorId)
  }

  /**
   * ## findOneByUUID
   *
   * Получить черновик по **uuid**
   *
   * @param uuid
   * */

  fun findOneByUUID(uuid: String): General {
    return this.generalRepository.findByStatusAndUuid(GStatus.DRAFT, UUID.fromString(uuid))
      ?: throw ResourceNotFoundException("Черновик не найден.")
  }

  /**
   * ## create
   *
   * Создание черновика.
   *
   * @param authorId
   * @param payload
   * */

  private fun create(authorId: String, payload: DraftPayload): General {
    val current = Optional.ofNullable(this.mapper.getMapper().map(payload, General::class.java)).apply {
      this.get().date = ZonedDateTime.now(ZoneOffset.UTC)
      this.get().status = GStatus.DRAFT
      this.get().authorId = authorId
    }
    return this.generalRepository.save(current.get())
  }

  /**
   * ## update
   *
   * Обновление черновика.
   *
   * @param uuid
   * @param payload
   * */

  private fun update(uuid: String, payload: DraftPayload): General {
    val current = this.generalRepository.findByStatusAndUuid(GStatus.DRAFT, UUID.fromString(uuid)).apply {
      this?.title = payload.title
      this?.content = payload.content
      this?.date = ZonedDateTime.now(ZoneOffset.UTC)
    } ?: throw ResourceNotFoundException("Черновик не найден.")
    return this.generalRepository.save(current)
  }

  /**
   * ## save
   *
   * Сохранение черновика.
   *
   * @param uuid
   * @param payload
   * */

  fun save(uuid: String?, payload: DraftPayload, request: HttpServletRequest): General? {
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

  fun archive(uuid: String): General? {
    val current = this.generalRepository.findByStatusAndUuid(GStatus.DRAFT, UUID.fromString(uuid)).apply {
      this?.status = GStatus.ARCHIVE
    } ?: throw ResourceNotFoundException("Черновик не найден.")
    return current.let { this.generalRepository.save(it) }
  }

}