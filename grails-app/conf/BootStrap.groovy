import com.sharefile.auth.User
import com.sharefile.auth.Role
import com.sharefile.auth.UserRole

class BootStrap {

    def init = { servletContext ->
        // spring security
        def roleAdmin = new Role(authority: 'ROLE_ADMIN').save()
        def roleUser = new Role(authority: 'ROLE_USER').save()

        def userAdmin = new User(username: 'admin', password: 'admin', enabled: true).save()
        def userUser = new User(username: 'user', password: 'user', enabled: true).save()

        def adminRole = new UserRole(user: userAdmin, role: roleAdmin).save()
        def userRole = new UserRole(user: userUser, role: roleUser).save()
    }


    def destroy = {
    }
}
