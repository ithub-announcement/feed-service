package ru.itcollege.feedservice.core.api.authorization

import io.grpc.ManagedChannelBuilder
import org.springframework.beans.factory.annotation.Value
import ru.itcollege.grpc.authorization.AuthorizationServiceGrpc
import ru.itcollege.grpc.authorization.JWTPayload
import ru.itcollege.grpc.authorization.User

/**
 * # AuthorizationGrpcClient
 *
 * Сервис для общения с user-service по GRPC.
 *
 * @author Чехонадских Дмитрий <loseex@vk.com>
 * */

class AuthorizationGrpcClient {
  @Value("\${api.authorization.url}")
  private lateinit var host: String

  @Value("\${api.authorization.port}")
  private var port: Int = 0

  private var channel = ManagedChannelBuilder.forAddress(this.host, this.port).usePlaintext().build()
  private var stub = AuthorizationServiceGrpc.newBlockingStub(channel)

  /**
   * ## getUserByAccess
   *
   * Получить пользователя по токену.
   *
   * @param token
   * */

  fun getUserByAccess(token: String): User? {
    val payload = JWTPayload.newBuilder().setAccess(token).build()
    return this.stub.getUserByAccess(payload)
  }
}