package atech.reg.backend.session.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import atech.reg.backend.session.group.GroupEntity;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;
    // @Autowired
    // private GroupRepository repoGroup;

    private ObjectMapper objectMapper = new ObjectMapper();

    public List<UserEntity> get() {
        return repo.findByDeletedOrderByNameAsc(false);
    }

    public UserEntity getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // public void insert(String jsonString) {
    //     try {
    //         ObjectMapper objectMapper = new ObjectMapper();
    //         JsonNode json = objectMapper.readTree(jsonString);

    //         String login = json.get("login").asText();
    //         String password = json.get("password").asText();
    //         String name = json.get("name").asText();
    //         String description = json.get("description").asText();

    //         UserEntity entity = new UserEntity();
    //         entity.setLogin(login);
    //         entity.setPassword(password);
    //         entity.setName(name);
    //         entity.setDescription(description);
    //         repo.save(entity);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // public void editUser(String jsonString) {
    //     try {
    //         ObjectMapper objectMapper = new ObjectMapper();
    //         JsonNode json = objectMapper.readTree(jsonString);

    //         Long id = json.get("id").asLong();
    //         String login = json.get("login").asText();
    //         String password = json.get("password").asText();
    //         String name = json.get("name").asText();
    //         String description = json.get("description").asText();

    //         Set<GroupEntity> groups = new HashSet<>();
    //         for (JsonNode groupId : json.get("groups"))
    //             groups.add(repoGroup.findById(groupId.asLong()));

    //         UserEntity entity = getUser(id);
    //         entity.setLogin(login);
    //         entity.setPassword(password);
    //         entity.setName(name);
    //         entity.setDescription(description);
    //         entity.setGroups(groups);
    //         repo.save(entity);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // public void deleteUser(String jsonString) {
    //     try {
    //         ObjectMapper objectMapper = new ObjectMapper();
    //         JsonNode json = objectMapper.readTree(jsonString);

    //         Long id = json.get("id").asLong();

    //         UserEntity entity = getUser(id);
    //         entity.setDeleted(true);
    //         repo.save(entity);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    public List<Long> authUser(String jsonString) {
        List<Long> list = new ArrayList<>();
        try {
            JsonNode json = objectMapper.readTree(jsonString);

            String login = json.get("login").asText();
            String password = json.get("password").asText();

            UserEntity entity = repo.findByLoginAndPassword(login, password);
            Set<GroupEntity> groups = entity.getGroups();
            for (GroupEntity group : groups)
                list.add(group.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}