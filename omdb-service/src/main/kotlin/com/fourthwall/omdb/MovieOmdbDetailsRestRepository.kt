package com.fourthwall.omdb

import com.fourthwall.entity.OmdbDetail
import com.fourthwall.exception.OmdbClientException
import com.fourthwall.exception.OmdbServerException
import com.fourthwall.omdb.response.OmdbMovieResponse
import com.fourthwall.repository.MovieOmdbDetailsRepository
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.isClientError
import com.github.kittinunf.fuel.jackson.responseObject
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MovieOmdbDetailsRestRepository(
    @Value("\${OMDB_API_KEY}")
    private val omdbApiKey: String,
    @Value("\${omdb.movie.url}")
    private val omdbMovieUrl: String
) : MovieOmdbDetailsRepository {

    override fun getMovieOmdbDetails(omdbId: String): OmdbDetail {
        val omdbParameter = listOf("apiKey" to omdbApiKey, "i" to omdbId)

        val(_, response, result) = Fuel.get(omdbMovieUrl, omdbParameter).responseObject<OmdbMovieResponse>()

        return result.fold(
            success = { omdbDetail -> omdbDetail.toOmdbDetail() },
            failure = { fuelError ->
                if (response.isClientError) {
                    throw OmdbClientException(fuelError.message)
                } else {
                    throw OmdbServerException(fuelError.message)
                }
            }
        )
    }
}
