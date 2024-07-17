package ru.itcollege.feedservice.core.api.authorization

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

/**
 * # RestClientAuthorization
 *
 * @author Горелов Дмитрий.
 * */

@Service
@PropertySource("classpath:application.properties")
class RestClientAuthorization {
  @Value("api.authorization.baseUrl")
  private lateinit var baseUrl: String;

  private fun request(token: String, path: String): String? {
    try {
      val restTemplate: RestTemplate = RestTemplate();
      val httpHeaders: HttpHeaders = HttpHeaders();

      httpHeaders.contentType = MediaType.APPLICATION_JSON;

      val request = HttpEntity(token, httpHeaders);
      return restTemplate.postForObject(this.baseUrl + path, request, String::class.java)
        ?.replace("\"", "")
        ?.replace("}", "")

    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }

  fun getUsername(token: String): String? {
    return this.request(token, "/user/token")?.split("data:")?.get(1);
  }

}