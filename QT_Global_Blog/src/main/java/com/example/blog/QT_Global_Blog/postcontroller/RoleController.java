package demo;

import com.example.blog.QT_Global_Blog.DaoRepository.RoleRepository;
import com.example.blog.QT_Global_Blog.userEntities.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleRepository roleRepository;
    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
        List<Role> allRoles = roleRepository.findAll();
        return ResponseEntity.ok(allRoles);
    }

    @PostMapping("/create-role")
    public ResponseEntity<Role> createUser(@RequestBody Role role) {

//            check if the role already exists
        Optional<Role> existingRole =
                roleRepository
                        .findByName(role.getName());
        if (existingRole.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        Role newRole= roleRepository.save(role);


//      save a new role if email does not exist
        return ResponseEntity.status(HttpStatus.CREATED).body(newRole);
    }
}

