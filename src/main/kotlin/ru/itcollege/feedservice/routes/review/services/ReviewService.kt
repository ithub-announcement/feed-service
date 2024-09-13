package ru.itcollege.feedservice.routes.review.services

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service
import ru.itcollege.feedservice.core.api.authorization.AuthorizationGrpcClient
import ru.itcollege.feedservice.routes.review.models.entities.Review
import ru.itcollege.feedservice.routes.review.models.enums.RStatus
import ru.itcollege.feedservice.routes.review.repositories.ReviewRepository
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*

@Service
class ReviewService(
  private var reviewRepository: ReviewRepository,
  private var authorizationGrpcClient: AuthorizationGrpcClient
) {

  /**
   * ## findAll
   *
   * Получить список заявок на публикацию.
   *
   * */

  fun findAll(): MutableList<Review> {
    return this.reviewRepository.findAll()
  }

  /**
   * ## findOneByUUID
   *
   * Получить одну заявку на публикацию по **uuid**.
   *
   * @param uuid
   * */

  fun findOneByUUID(uuid: String): Optional<Review> {
    return this.reviewRepository.findById(UUID.fromString(uuid))
  }

  /**
   * ## create
   *
   * Создать заявку на публикацию.
   *
   * @param uuid (Черновика)
   * @param request
   * */

  fun create(uuid: String, request: HttpServletRequest): Review {
    val authorId: String = this.authorizationGrpcClient.getUserByAccess(request.getHeader("Authorization")).toString()
    val current = Review().apply {
      this.uuidDraft = UUID.fromString(uuid)
      this.status = RStatus.OPEN
      this.authorId = authorId
      this.date = ZonedDateTime.now(ZoneOffset.UTC)
    }
    return this.reviewRepository.save(current)
  }

  /**
   * ## approve
   *
   * Одобрить заявку на публикацию.
   *
   * @param uuid
   * */

  fun approve(uuid: String): Review {
    val current = this.reviewRepository.findById(UUID.fromString(uuid)).apply {
      this.get().status = RStatus.APPROVED
    }
    return this.reviewRepository.save(current.get())
  }

  /**
   * ## reject
   *
   * Отклонить заявку на публикацию.
   *
   * @param uuid
   * */

  fun reject(uuid: String): Review {
    val current = this.reviewRepository.findById(UUID.fromString(uuid)).apply {
      this.get().status = RStatus.REJECTED
    }
    return this.reviewRepository.save(current.get())
  }

  /**
   * ## delete
   *
   * Удалить заявку на публикацию.
   *
   * @param uuid
   * */

  fun delete(uuid: String) {
    return this.reviewRepository.deleteById(UUID.fromString(uuid))
  }

}