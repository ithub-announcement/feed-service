package ru.itcollege.feedservice.routes.drafts.controllers

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.itcollege.feedservice.routes.drafts.services.DraftsService

@Tag(name = "Черновики")
@RestController
@RequestMapping("/drafts")
class DraftsController(private var draftsService: DraftsService) {
  // todo
}