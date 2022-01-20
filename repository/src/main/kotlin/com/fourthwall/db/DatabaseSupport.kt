package com.fourthwall.db

import org.hsqldb.util.DatabaseManagerSwing
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import javax.sql.DataSource

/**
 * This class is just a support for development and should not be used in production environment
 */
@Component
class DatabaseSupport(
    @Value("\${spring.datasource.url}")
    private val jdbcUrl: String,
    private val dataSource: DataSource,
) {

    @EventListener(ApplicationReadyEvent::class)
    fun populateDatabase() {
        if (!"test".equals(System.getenv("spring_profiles_active"), true)) {
            val resourceDatabasePopulator =
                ResourceDatabasePopulator(false, false, "UTF-8", ClassPathResource("support/data.sql"))
            resourceDatabasePopulator.execute(dataSource)
        }
    }

    @PostConstruct
    fun init() {
        if ("debug".equals(System.getenv("spring_profiles_active"), true)) {
            System.setProperty("java.awt.headless", "false")
            DatabaseManagerSwing.main(arrayOf("--url", jdbcUrl, "--noexit"))
        }
    }
}
