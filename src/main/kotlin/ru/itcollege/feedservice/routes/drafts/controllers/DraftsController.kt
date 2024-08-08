package ru.itcollege.feedservice.routes.drafts.controllers

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import ru.itcollege.feedservice.core.domain.models.entities.General
import ru.itcollege.feedservice.routes.drafts.models.dto.DraftPayload
import ru.itcollege.feedservice.routes.drafts.services.DraftsService

@Tag(name = "Черновики")
@RestController
@RequestMapping("/drafts")
class DraftsController(private var draftsService: DraftsService) {
  @GetMapping
  fun findAllByAuthorId(): MutableList<General> {
    return this.draftsService.findAllByAuthorId()
  }

  @GetMapping("{uuid}")
  fun findOneByUUID(@PathVariable uuid: String): General {
    return this.draftsService.findOneByUUID(uuid)
  }

  @PostMapping("/save")
  fun saveDraft(@RequestParam(required = false) uuid: String?, @RequestBody body: DraftPayload): General? {
    return this.draftsService.save(uuid, body)
  }

  @DeleteMapping("/delete/{uuid}")
  fun archiveDraft(@PathVariable uuid: String): General? {
    return this.draftsService.archive(uuid)
  }
}