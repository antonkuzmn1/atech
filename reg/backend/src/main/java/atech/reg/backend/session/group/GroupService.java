package atech.reg.backend.session.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    private GroupRepository repo;
    // @Autowired
    // private UserRepository repoUser;

    // private ObjectMapper objectMapper = new ObjectMapper();

    public List<GroupEntity> get() {
        return repo.findByDeletedOrderByNameAsc(false);
    }

    public GroupEntity getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // public void insert(String jsonString) {
    //     try {
    //         GroupEntity entity = objectMapper.readValue(jsonString, GroupEntity.class);
    //         repo.save(new GroupEntity(
    //                 entity.getName(),
    //                 entity.getDescription()));
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // public void edit(String jsonString) {
    //     try {
    //         JsonNode json = objectMapper.readTree(jsonString);

    //         Long id = json.get("id").asLong();
    //         String name = json.get("name").asText();
    //         String description = json.get("description").asText();

    //         Set<UserEntity> users = new HashSet<>();
    //         for (JsonNode userId : json.get("users"))
    //             users.add(repoUser.findById(userId.asLong()).orElse(null));

    //         GroupEntity entity = getById(id);
    //         entity.setName(name);
    //         entity.setDescription(description);
    //         entity.setUsers(users);
    //         repo.save(entity);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // public void delete(String jsonString) {
    //     try {
    //         ObjectMapper objectMapper = new ObjectMapper();
    //         JsonNode json = objectMapper.readTree(jsonString);

    //         Long id = json.get("id").asLong();

    //         GroupEntity entity = getById(id);
    //         entity.setDeleted(true);
    //         repo.save(entity);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

}
