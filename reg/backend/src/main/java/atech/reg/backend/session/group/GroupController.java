package atech.reg.backend.session.group;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session/group")
public class GroupController {

    private final GroupService service;

    public GroupController(GroupService service) {
        this.service = service;
    }

    @GetMapping
    public List<GroupEntity> getGroups() {
        return service.getGroups();
    }

    @PostMapping("/new")
    public void newGroup(@RequestBody String jsonString) {
        service.newGroup(jsonString);
    }

    @PostMapping("/edit")
    public void editGroup(@RequestBody String jsonString) {
        service.editGroup(jsonString);
    }

    @PostMapping("/delete")
    public void deleteGroup(@RequestBody String jsonString) {
        service.deleteGroup(jsonString);
    }

}
