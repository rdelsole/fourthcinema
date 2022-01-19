package com.fourthwall.repository.jpa

import com.fourthwall.repository.model.MovieEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface MovieJPARepository : CrudRepository<MovieEntity, UUID>
