package atech.reg.backend.buh.table.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public List<TableAllEntity> get(@RequestBody String data) {
        return service.get(data);
    }

    @PostMapping("/insert")
    public boolean insert(@RequestBody String data) {
        return service.insert(data);
    }

    @PostMapping("/edit")
    public boolean edit(HttpSession session, @RequestBody String data) {
        return service.edit(session, data);
    }

    // @PostMapping("/delete")
    // public boolean delete(HttpSession session, @RequestBody String data) {
    //     return service.delete(session, data);
    // }

}
