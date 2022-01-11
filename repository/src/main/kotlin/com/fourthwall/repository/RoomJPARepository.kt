package com.fourthwall.repository

import com.fourthwall.repository.model.Movie
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface RoomJPARepository : CrudRepository<Movie, UUID>
