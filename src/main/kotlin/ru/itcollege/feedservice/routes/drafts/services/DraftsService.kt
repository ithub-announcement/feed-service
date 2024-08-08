package ru.itcollege.feedservice.routes.drafts.services

import org.springframework.stereotype.Service
import ru.itcollege.feedservice.core.config.MapperConfig
import ru.itcollege.feedservice.core.domain.repositories.GeneralRepository

@Service
class DraftsService(private var generalRepository: GeneralRepository, private var mapper: MapperConfig) {
  // todo
}