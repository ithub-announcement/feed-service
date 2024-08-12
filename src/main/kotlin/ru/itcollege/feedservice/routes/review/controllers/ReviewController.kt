package ru.itcollege.feedservice.routes.review.controllers

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.*
import ru.itcollege.feedservice.routes.review.models.entities.Review
import ru.itcollege.feedservice.routes.review.services.ReviewService
import java.util.*

@Tag(name = "Модерация")
@RestController
@RequestMapping("/reviews")
class ReviewController(private var reviewService: ReviewService) {

  @GetMapping
  @Operation(summary = "Получить список заявок на публикацию.")
  fun findAll(): MutableList<Review> {
    return this.reviewService.findAll()
  }

  @GetMapping("{uuid}")
  @Operation(summary = "Получить одну заявку на публикацию по uuid.")
  fun findOneByUUID(@PathVariable uuid: String): Optional<Review> {
    return this.reviewService.findOneByUUID(uuid)
  }

  @PostMapping("/send-to-review/{uuid}")
  @Operation(summary = "Отправить черновик на рассмотрение.")
  fun sendToReview(@PathVariable uuid: String, request: HttpServletRequest): Review {
    return this.reviewService.create(uuid, request)
  }

  @PostMapping("/approve/{uuid}")
  @Operation(summary = "Одобрить заявку на публикацию по uuid.")
  fun approve(@PathVariable uuid: String): Review {
    return this.reviewService.approve(uuid)
  }

  @PostMapping("/reject/{uuid}")
  @Operation(summary = "Отклонить заявку на публикацию по uuid.")
  fun reject(@PathVariable uuid: String): Review {
    return this.reviewService.reject(uuid)
  }

  @DeleteMapping("/delete/{uuid}")
  @Operation(summary = "Удалить заявку на публикацию по uuid.")
  fun delete(@PathVariable uuid: String) {
    return this.reviewService.delete(uuid)
  }
}