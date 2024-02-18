package atech.reg.backend.session.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserEntity> getUsers() {
        return service.getUsers();
    }

    @PostMapping("/new")
    public void newUser(@RequestBody String jsonString) {
        service.newUser(jsonString);
    }

    @PostMapping("/edit")
    public void editUser(@RequestBody String jsonString) {
        service.editUser(jsonString);
    }

    @PostMapping("/delete")
    public void deleteUser(@RequestBody String jsonString) {
        service.deleteUser(jsonString);
    }

}
