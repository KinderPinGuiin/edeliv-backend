package fr.univrouen.edeliv.repository

import fr.univrouen.edeliv.entity.Role
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * The role repository allows us to retrieve information about an API user roles.
 */
@Repository
interface RoleRepository : CrudRepository<Role, String>