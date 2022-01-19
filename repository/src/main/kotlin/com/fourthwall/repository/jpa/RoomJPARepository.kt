package com.fourthwall.repository.jpa

import com.fourthwall.repository.model.RoomEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface RoomJPARepository : CrudRepository<RoomEntity, UUID>
