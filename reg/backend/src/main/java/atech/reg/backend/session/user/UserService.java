package atech.reg.backend.session.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import atech.reg.backend.session.group.GroupEntity;
import atech.reg.backend.session.group.GroupRepository;

@Service
public class UserService {

    private final UserRepository repo;
    private final GroupRepository repoGroup;

    public UserService(
            UserRepository repo,
            GroupRepository repoGroup) {
        this.repo = repo;
        this.repoGroup = repoGroup;
    }

    public List<UserEntity> getUsers() {
        return repo.findByDeletedOrderByNameAsc(false);
    }

    public UserEntity getUser(Long id) {
        return repo.findById(id);
    }

    public void newUser(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode json = objectMapper.readTree(jsonString);

            String login = json.get("login").asText();
            String password = json.get("password").asText();
            String name = json.get("name").asText();
            String description = json.get("description").asText();

            UserEntity entity = new UserEntity();
            entity.setLogin(login);
            entity.setPassword(password);
            entity.setName(name);
            entity.setDescription(description);
            repo.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editUser(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode json = objectMapper.readTree(jsonString);

            Long id = json.get("id").asLong();
            String login = json.get("login").asText();
            String password = json.get("password").asText();
            String name = json.get("name").asText();
            String description = json.get("description").asText();

            Set<GroupEntity> groups = new HashSet<>();
            for (JsonNode groupId : json.get("groups"))
                groups.add(repoGroup.findById(groupId.asLong()));

            UserEntity entity = getUser(id);
            entity.setLogin(login);
            entity.setPassword(password);
            entity.setName(name);
            entity.setDescription(description);
            entity.setGroups(groups);
            repo.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode json = objectMapper.readTree(jsonString);

            Long id = json.get("id").asLong();

            UserEntity entity = getUser(id);
            entity.setDeleted(true);
            repo.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Long> authUser(String jsonString) {
        List<Long> list = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
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