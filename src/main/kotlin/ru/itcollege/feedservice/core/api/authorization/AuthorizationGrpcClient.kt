package ru.itcollege.feedservice.core.api.authorization

import io.grpc.ManagedChannelBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.itcollege.grpc.authorization.AuthorizationServiceGrpc
import ru.itcollege.grpc.authorization.JWTPayload

/**
 * # AuthorizationGrpcClient
 *
 * Сервис для общения с user-service по GRPC.
 *
 * @author Чехонадских Дмитрий <loseex@vk.com>
 * */

@Service
class AuthorizationGrpcClient {
  @Value("\${api.authorization.url}")
  private lateinit var host: String

  @Value("\${api.authorization.port}")
  private var port: Int = 0

  /**
   * ## getUserByAccess
   *
   * Получить пользователя по токену.
   *
   * @param token
   * */

  fun getUserByAccess(token: String): String? {
    val channel = ManagedChannelBuilder.forAddress(this.host, this.port).usePlaintext().build()
    val stub = AuthorizationServiceGrpc.newBlockingStub(channel)

    val payload = JWTPayload.newBuilder().setAccess(token).build()
    return stub.getUserByAccess(payload).uid
  }
}