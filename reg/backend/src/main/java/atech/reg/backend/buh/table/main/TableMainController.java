package atech.reg.backend.buh.table.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/buh/table/main")
public class TableMainController {

    @Autowired
    private TableMainService service;

    @PostMapping
    public List<TableMainEntity> getAll(@RequestBody String data) {
        return service.getAll(data);
    }

    @PostMapping("/edit")
    public boolean edit(HttpSession session, @RequestBody String jsonString) {
        return service.edit(session, jsonString);
    }

}
