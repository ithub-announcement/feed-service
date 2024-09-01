package ru.itcollege.feedservice.routes.announcements.controllers

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.itcollege.feedservice.core.domain.models.entities.General
import ru.itcollege.feedservice.routes.announcements.services.AnnouncementsService

@Tag(name = "Публикации")
@RestController
@RequestMapping("/announcements")
class AnnouncementsController(private var announcementsService: AnnouncementsService) {

  @GetMapping()
  fun findAllAnnouncements(): ResponseEntity<MutableList<General>> {
    return this.announcementsService.findAll()
  }

  @GetMapping("{uuid}")
  fun findOneAnnouncementByUUID(@PathVariable uuid: String): ResponseEntity<General> {
    return this.announcementsService.findOneByUUID(uuid)
  }

}