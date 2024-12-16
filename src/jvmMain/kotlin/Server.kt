import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

val exampleItems = listOf(
    ExampleItem(1, "Apple"),
    ExampleItem(2, "Pear"),
    ExampleItem(3, "Orange")
)

fun main() {
    embeddedServer(Netty, API_PORT) {
        install(ContentNegotiation) {
            json()
        }
        install(CORS) {
            allowMethod(HttpMethod.Get)
            allowMethod(HttpMethod.Post)
            allowMethod(HttpMethod.Put)
            allowMethod(HttpMethod.Delete)
            anyHost()
        }
        install(Compression) {
            gzip()
        }
        routing {
            route("/examples") {
                get {
                    call.respond(exampleItems)
                }
            }
        }
    }.start(wait = true)
}