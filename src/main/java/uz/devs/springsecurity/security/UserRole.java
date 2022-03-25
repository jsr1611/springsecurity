package uz.devs.springsecurity.security;

import com.google.common.collect.Sets;
import lombok.Getter;

import static uz.devs.springsecurity.security.UserPermission.*;

import java.util.Set;
@Getter
public enum UserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE));

    private final Set<UserPermission> permission;

    UserRole(Set<UserPermission> permission){
        this.permission = permission;
    }
}
