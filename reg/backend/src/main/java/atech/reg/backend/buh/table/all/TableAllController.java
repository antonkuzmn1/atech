package atech.reg.backend.buh.table.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/buh/table/all")
public class TableAllController {

    @Autowired
    private TableAllService service;

    @GetMapping
    public List<TableAllEntity> get() {
        return service.get();
    }

    @PostMapping("/insert")
    public boolean insert(HttpSession session, @RequestBody String jsonString) {
        return service.insert(session, jsonString);
    }

    @PostMapping("/edit")
    public boolean edit(HttpSession session, @RequestBody String jsonString) {
        return service.edit(session, jsonString);
    }

    @PostMapping("/delete")
    public boolean delete(HttpSession session, @RequestBody String jsonString) {
        return service.delete(session, jsonString);
    }

}
