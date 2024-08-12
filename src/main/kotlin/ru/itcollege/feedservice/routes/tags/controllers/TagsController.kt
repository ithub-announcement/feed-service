package ru.itcollege.feedservice.routes.tags.controllers

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import ru.itcollege.feedservice.routes.tags.models.dto.TagPayload
import ru.itcollege.feedservice.routes.tags.services.TagsService
import java.util.*

@Tag(name = "Категории")
@RestController
@RequestMapping("/tags")
class TagsController(private var tagsService: TagsService) {

  @GetMapping
  fun findAll(): MutableList<ru.itcollege.feedservice.routes.tags.models.entities.Tag> {
    return this.tagsService.findAll()
  }

  @GetMapping("{id}")
  fun findOneById(@PathVariable id: String): Optional<ru.itcollege.feedservice.routes.tags.models.entities.Tag> {
    return this.tagsService.findOneById(id.toLong())
  }

  @PostMapping("/create")
  fun create(@RequestBody body: TagPayload): ru.itcollege.feedservice.routes.tags.models.entities.Tag {
    return this.tagsService.create(body)
  }

  @DeleteMapping("/delete/{id}")
  fun delete(@PathVariable id: String) {
    return this.tagsService.delete(id.toLong())
  }

  @PutMapping("/edit/{id}")
  fun update(
    @PathVariable id: String,
    @RequestBody body: TagPayload
  ): ru.itcollege.feedservice.routes.tags.models.entities.Tag {
    return this.tagsService.update(id.toLong(), body)
  }
}