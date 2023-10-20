package fr.univrouen.edeliv.entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * Class representing an API user.
 */
@Entity
class User(
    /**
     * The user's ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long,

    /**
     * The user's username.
     */
    @JvmField // Prevent kotlin declaration clash
    var username: String,

    /**
     * The user's password.
     */
    @JvmField // Prevent kotlin declaration clash
    var password: String,

    /**
     * The user's bearer token.
     */
    var token: String?,

    @ManyToMany(fetch = FetchType.EAGER)
    var roles: MutableSet<Role> = mutableSetOf()
) : UserDetails {

    constructor(): this(0, "", "", "")

    /**
     * @return The user API roles.
     */
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        // Get the user roles and add them to a GrantedAuthority set
        val authorities = mutableSetOf<GrantedAuthority>()
        for (role in this.roles) {
            authorities.add(SimpleGrantedAuthority(role.name))
        }

        return authorities
    }

    override fun getPassword(): String = this.password

    override fun getUsername(): String = this.username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

}