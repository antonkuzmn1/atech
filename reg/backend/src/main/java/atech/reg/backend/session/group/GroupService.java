package atech.reg.backend.session.group;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import atech.reg.backend.session.user.UserEntity;
import atech.reg.backend.session.user.UserRepository;

@Service
public class GroupService {

    private final GroupRepository repo;
    private final UserRepository repoUser;

    public GroupService(
            GroupRepository repo,
            UserRepository repoUser) {
        this.repo = repo;
        this.repoUser = repoUser;
    }

    public List<GroupEntity> getGroups() {
        return repo.findByDeletedOrderByNameAsc(false);
    }

    public GroupEntity getGroup(Long id) {
        return repo.findById(id);
    }

    public void newGroup(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode json = objectMapper.readTree(jsonString);

            String name = json.get("name").asText();
            String description = json.get("description").asText();

            GroupEntity entity = new GroupEntity();
            entity.setName(name);
            entity.setDescription(description);
            repo.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editGroup(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode json = objectMapper.readTree(jsonString);

            Long id = json.get("id").asLong();
            String name = json.get("name").asText();
            String description = json.get("description").asText();

            Set<UserEntity> users = new HashSet<>();
            for (JsonNode userId : json.get("users"))
                users.add(repoUser.findById(userId.asLong()));

            GroupEntity entity = getGroup(id);
            entity.setName(name);
            entity.setDescription(description);
            entity.setUsers(users);
            repo.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteGroup(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode json = objectMapper.readTree(jsonString);

            Long id = json.get("id").asLong();

            GroupEntity entity = getGroup(id);
            entity.setDeleted(true);
            repo.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
