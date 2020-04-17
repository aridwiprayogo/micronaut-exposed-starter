package micronaut.exposed.starter.config

import com.zaxxer.hikari.HikariDataSource
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import org.jetbrains.exposed.sql.Database
import javax.inject.Singleton

@Factory
class DBSettingFactory {

    @Bean
    @Singleton
    fun db(): Database {
        val dataSources: HikariDataSource = HikariDataSource().apply {
            jdbcUrl= "jdbc:postgresql://localhost:5432/postgres"
            username= "postgres"
            password= "root"
            driverClassName= "org.postgresql.Driver"
            schema = "news"
        }
        return Database.connect(dataSources)
    }
}