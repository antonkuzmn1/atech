package atech.reg.backend.buh.table.all;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import atech.reg.backend.buh.dropdown.BuhDropdownService;
import jakarta.servlet.http.HttpSession;

@Service
public class TableAllService {

    @Autowired
    private TableAllRepository repo;
    @Autowired
    private BuhDropdownService dropdownService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private boolean allow(HttpSession session) {
        @SuppressWarnings("unchecked")
        List<Long> groupIds = (List<Long>) session.getAttribute("groups");
        boolean allow = (groupIds.contains(1L) || groupIds.contains(2L));
        return allow;
    }

    public List<TableAllEntity> get() {
        return repo.findAll();
    }

    public TableAllEntity getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public boolean insert(HttpSession session, String jsonString) {
        try {
            TableAllEntity entity = objectMapper.readValue(jsonString, TableAllEntity.class);
            if (!allow(session)) {
                entity.setId(null);
                entity.setMark(dropdownService.getMarkById(0L));
                entity.setDeleted(false);
            }
            repo.save(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean edit(HttpSession session, String jsonString) {
        try {
            TableAllEntity entityNew = objectMapper.readValue(jsonString, TableAllEntity.class);
            TableAllEntity entity = getById(entityNew.getId());
            if (allow(session))
                BeanUtils.copyProperties(entityNew, entity, "id");
            else
                BeanUtils.copyProperties(entityNew, entity, "id",
                        "mark",
                        "deleted");
            repo.save(entity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(HttpSession session, String jsonString) {
        try {
            getById(objectMapper.readTree(jsonString).get("id").asLong()).setDeleted(true);;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
