import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

val jsonClient = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}

private val urlPrefix = "$SCHEME://$HOST:$API_PORT/examples"

suspend fun getItems(): List<ExampleItem> {
    return jsonClient.get(urlPrefix).body()
}

suspend fun getItem(id: String): ExampleItem {
    return jsonClient.get("$urlPrefix/$id").body()
}

suspend fun addItem(item: ExampleItem) {
    jsonClient.put(urlPrefix) {
        contentType(ContentType.Application.Json)
        setBody(item)
    }
}

suspend fun removeItem(item: ExampleItem) {
    jsonClient.delete("$urlPrefix/${item.id}")
}
