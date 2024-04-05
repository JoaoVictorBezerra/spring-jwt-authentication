package com.example.authapi.security;

import com.example.authapi.constants.role.Roles;
import com.example.authapi.entity.user.exceptions.RoleNotAllowedException;
import com.example.authapi.security.anotations.HasAdminRole;
import com.example.authapi.security.anotations.HasAnyRole;
import com.example.authapi.security.anotations.HasOrganizerRole;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomRoleAspect {

    @Before("@annotation(hasAdminRole)")
    public void checkHasAdminRole(HasAdminRole hasAdminRole) {
        if (!hasRole(Roles.ADMIN)) {
            throw new RoleNotAllowedException("Only administrators can do this request");
        }
    }

    @Before("@annotation(hasOrganizerRole)")
    public void checkHasOrganizerRole(HasOrganizerRole hasOrganizerRole) {
        if (!hasRole(Roles.ORGANIZER) && !hasRole(Roles.ADMIN)) {
            throw new RoleNotAllowedException("Only organizers can do this request.");
        }
    }

    @Before("@annotation(hasAnyRole)")
    public void checkHasAnyRole(HasAnyRole hasAnyRole) {
        if (!hasRole(Roles.USER) && !hasRole(Roles.ORGANIZER) && !hasRole(Roles.ADMIN)) {
            throw new RoleNotAllowedException("Access denied!");
        }
    }

    private boolean hasRole(String role) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(role));
    }
}