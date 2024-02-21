package atech.reg.backend.buh.dropdown;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buh/dropdown")
public class BuhDropdownController {

    @Autowired
    private BuhDropdownService service;

    @GetMapping
    public Map<String, Object> get() {
        return service.get();
    }

}
