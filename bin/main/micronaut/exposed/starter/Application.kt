package micronaut.exposed.starter

import io.micronaut.context.annotation.Value
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.discovery.event.ServiceStartedEvent
import io.micronaut.runtime.Micronaut
import io.swagger.v3.oas.annotations.*
import io.swagger.v3.oas.annotations.info.*
import micronaut.exposed.starter.domain.Authors
import micronaut.exposed.starter.domain.PostEntity
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import javax.annotation.PostConstruct
import javax.inject.Singleton

@OpenAPIDefinition(
    info = Info(
            title = "micronaut-exposed-starter",
            version = "0.0"
    )
)
object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("micronaut.exposed.starter")
                .mainClass(Application.javaClass)
                .start()
    }

    @PostConstruct
    fun setupSchema(db: Database, @Value("\${schema.gen}") genSchema: Boolean){
        if (!genSchema){
            return
        }
        transaction {
            SchemaUtils.create(tables = *arrayOf(Authors, PostEntity))
        }
    }
}

@Singleton
class DoOnStartup(val database: Database,  @Value("\${schema.gen}") val genSchema: Boolean): ApplicationEventListener<ServiceStartedEvent>{
    override fun onApplicationEvent(event: ServiceStartedEvent?) {
        if (!genSchema)return
        transaction {
            SchemaUtils.create(tables = *arrayOf(Authors, PostEntity))
        }
    }

}
