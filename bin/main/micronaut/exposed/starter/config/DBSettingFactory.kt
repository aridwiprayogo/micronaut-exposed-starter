package micronaut.exposed.starter.config

import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import org.jetbrains.exposed.sql.Database
import javax.inject.Singleton
import javax.sql.DataSource

@Factory
class DBSettingFactory {

    @Bean
    @Singleton
    fun db(hikariDataSource: DataSource): Database {
        return Database.connect(hikariDataSource)
    }
}