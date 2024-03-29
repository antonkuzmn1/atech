package atech.reg.backend.buh.contractor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buh/contractor")
public class ContractorController {

    @Autowired
    private ContractorService service;

    @GetMapping
    public List<ContractorEntity> get() {
        return service.get();
    }

}
