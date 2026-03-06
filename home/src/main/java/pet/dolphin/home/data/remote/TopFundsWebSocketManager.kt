package pet.dolphin.home.data.remote

import io.ktor.client.HttpClient
import kotlinx.coroutines.delay
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
import org.json.JSONObject
import pet.dolphin.home.data.mappers.toDomain
import pet.dolphin.home.data.remote.dto.FundPreviewWsDto
import pet.dolphin.home.domain.model.FundPreviewWs
import kotlin.time.Duration

class TopFundsWebSocketManager(
    //private val sclient: OkHttpClient
) {
    private var lastSearchFund: List<String> = emptyList()
    private var webSocket: WebSocket? = null

    private val _events = MutableSharedFlow<FundPreviewWs>(
        replay = 0,
        extraBufferCapacity = 64
    )
    val events: SharedFlow<FundPreviewWs> = _events.asSharedFlow()
    private val client = OkHttpClient()

    private val json = Json {
        ignoreUnknownKeys = true
    }
    fun connect() {
        if (webSocket != null) return

        val wsUrl = "wss://stream.binance.com:9443/ws"

        val request = Request.Builder()
            .url(wsUrl)
            .build()

        webSocket = client.newWebSocket(request, listener)
    }

    fun subscribe(fundsSymbols: List<String>) {
        lastSearchFund = fundsSymbols

        val payload = mapOf(
            "method" to "SUBSCRIBE",
            "params" to fundsSymbols,
            "id" to System.currentTimeMillis()
        )

        val jsonString = JSONObject(payload).toString()
        webSocket?.send(jsonString)
    }

    fun unsubscribe() {
        val payload = mapOf(
            "method" to "SUBSCRIBE",
            "params" to lastSearchFund,
            "id" to System.currentTimeMillis()
        )

        webSocket?.send(JSONObject(payload).toString())
    }

    private val listener = object: WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            println("WebSocket открыт")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            emitValue(text)
            println("Сообщение WS: $text")
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            reconnect()
            println("Ошибка WS: ${t.message}")
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            println("WebSocket закрылся: $reason")
        }
    }

    fun reconnect() {
        disconnect()
        connect()
    }

    private fun disconnect() {
        webSocket?.cancel()
        webSocket = null
    }

    private fun emitValue(text: String) {
        println("response: $text")

        if (text.contains("result")) {
            return
        }

        val response = json.decodeFromString<FundPreviewWsDto>(text)
        _events.tryEmit(response.toDomain())
    }
}