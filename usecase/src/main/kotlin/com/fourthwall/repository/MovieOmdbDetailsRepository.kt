package com.fourthwall.repository

import com.fourthwall.entity.OmdbDetail

interface MovieOmdbDetailsRepository {

    fun getMovieOmdbDetails(omdbId: String): OmdbDetail
}
