package pet.dolphin.home.data.remote

import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.serialization.json.Json
import okhttp3.Request
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import pet.dolphin.home.data.remote.dto.FundWsResponseDto

class TopFundsWebSocketManager(
    //private val sclient: OkHttpClient
) {
    private var socket: WebSocket? = null

    private val _events = MutableSharedFlow<List<FundWsResponseDto>>()
    val events: SharedFlow<List<FundWsResponseDto>> = _events.asSharedFlow()
    private val client = OkHttpClient()
    suspend fun connect() {
        if (socket != null) return
        val wsUrl = "wss://stream.binance.com:9443/stream?streams=\n" +
                "btcusdt@ticker/\n" +
                "ethusdt@ticker/\n" +
                "bnbusdt@ticker"

        val request = Request.Builder()
            .url(wsUrl)
            .build()

        client.newWebSocket(request, object: WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {

                println("WebSocket открыт")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                val response = Json.decodeFromString<List<FundWsResponseDto>>(text)
                println("Сообщение WS: $text")
                println("response: $response")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                println("Ошибка WS: ${t.message}")
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                println("WebSocket закрылся: $reason")
            }
        })
    }

    private suspend fun emitValue(response: List<FundWsResponseDto>) {
        _events.emitAll(response.map { it.data.toDomain() })
    }
}