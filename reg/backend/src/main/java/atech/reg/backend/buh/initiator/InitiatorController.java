package atech.reg.backend.buh.initiator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buh/initiator")
public class InitiatorController {

    @Autowired
    private InitiatorService service;

    @GetMapping
    public List<InitiatorEntity> get() {
        return service.get();
    }

}
