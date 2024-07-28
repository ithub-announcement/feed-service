package ru.itcollege.feedservice.core.config

import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MapperConfig {
  @Bean
  fun getMapper(): ModelMapper {
    val mapper: ModelMapper = ModelMapper()
    mapper.configuration.setMatchingStrategy(MatchingStrategies.STRICT)
    return mapper
  }
}