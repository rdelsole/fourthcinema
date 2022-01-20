package com.fourthwall

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
    locations = ["classpath:application-integrationtest.properties"]
)
@AutoConfigureMockMvc
@ComponentScan("com.fourthwall")
class UpdatePriceIntegrationTest {

    @LocalServerPort
    lateinit var port: Integer

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    @Sql(scripts = ["classpath:db/insert_movie_test.sql"])
    fun `should update price of a movie`() {
        val updateMovieBody = "{ \"price\": \"99.00\" }"

        mockMvc.perform(
            put("http://localhost:$port/admin/movie/20/price/30")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updateMovieBody)
        ).andExpect(status().isOk)

        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:$port/movie/20/times").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.id").value(20))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.moviePriceRoom[0].price").value("99.00"))
    }

    @Test
    fun `should return 404 when movie is not found`() {
        val updateMovieBody = "{ \"price\": \"99.00\" }"

        mockMvc.perform(
            put("http://localhost:$port/admin/movie/80/price/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updateMovieBody)
        ).andExpect(status().isNotFound)
    }

    @Test
    fun `should return 400 when price format is not invalid`() {
        val updateMovieBody = "{ \"price\": \"199.00\" }"

        mockMvc.perform(
            put("http://localhost:$port/admin/movie/80/price/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(updateMovieBody)
        ).andExpect(status().isBadRequest)
    }

    companion object {
        init {
            System.setProperty("OMDB_API_KEY", "simpleKey")
            System.setProperty("SPRING_PROFILES_ACTIVE", "test")
        }
    }
}
