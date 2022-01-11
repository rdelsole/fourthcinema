package com.fourthwall.db

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Profile
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
@Profile("!prd")
class DatabaseSupport(
    @Value("\${spring.datasource.url}")
    private val jdbcUrl: String,
    private val dataSource: DataSource
) {

    @EventListener(ApplicationReadyEvent::class)
    fun populateDatabase() {
        val resourceDatabasePopulator = ResourceDatabasePopulator(false, false, "UTF-8", ClassPathResource("support/data.sql"))
        resourceDatabasePopulator.execute(dataSource)
    }

    @PostConstruct
    fun init() {
        System.setProperty("java.awt.headless", "false")
        // Uncomment the below line to open a GUI to navigate on memory database
        // DatabaseManagerSwing.main(arrayOf("--url", jdbcUrl, "--noexit"))
    }
}
