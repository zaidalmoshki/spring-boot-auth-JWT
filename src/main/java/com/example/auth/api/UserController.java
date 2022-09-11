package com.example.auth.api;

import com.example.auth.AuthApplication;
import com.example.auth.domain.Role;
import com.example.auth.domain.User;
import com.example.auth.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private  final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return  ResponseEntity.ok().body(
                userService.getUsers()
        );
    }

    //hasAuthority('ROLE_ADMIN') , hasAnyAuthority('ROLE_ADMIN' , 'ROLE_USER' )
    @PostMapping("/user/save")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString()) ;
        return  ResponseEntity.created(uri).body(
                userService.saveUser(user)
        );
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri =URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString()) ;
        return  ResponseEntity.created(uri).body(
                userService.saveRole(role)
        );
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(),form.getRoleName());
        return ResponseEntity.ok().build();
    }

}
@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}
