package com.example.preapp.ioc

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.http.Header
import javax.inject.Inject

class AuthorisationInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.run {
            proceed(
                request()
                    .newBuilder()
                    .addHeader(
                        "x-api-key",
                        "live_g55L0WsNQKDD22I8mhz1A2WSl1saL40U6lPsQWPaxkkSbYvPfhcdb7IzXhOwuNIT"
                    )
                    .build()
            )
        }

}