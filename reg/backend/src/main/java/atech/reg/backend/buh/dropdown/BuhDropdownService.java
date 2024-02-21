package atech.reg.backend.buh.dropdown;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import atech.reg.backend.buh.dropdown.data.BuhDropdownAboutEntity;
import atech.reg.backend.buh.dropdown.data.BuhDropdownAboutRepository;
import atech.reg.backend.buh.dropdown.data.BuhDropdownControlEntity;
import atech.reg.backend.buh.dropdown.data.BuhDropdownControlRepository;
import atech.reg.backend.buh.dropdown.data.BuhDropdownMarkEntity;
import atech.reg.backend.buh.dropdown.data.BuhDropdownMarkRepository;
import atech.reg.backend.buh.dropdown.data.BuhDropdownStatusEntity;
import atech.reg.backend.buh.dropdown.data.BuhDropdownStatusRepository;

@Service
public class BuhDropdownService {

    @Autowired
    private BuhDropdownControlRepository control;
    @Autowired
    private BuhDropdownAboutRepository about;
    @Autowired
    private BuhDropdownMarkRepository mark;
    @Autowired
    private BuhDropdownStatusRepository status;

    public Map<String, Object> get() {
        Map<String, Object> map = new HashMap<>();
        map.put("control", control.findAllByOrderByIdAsc());
        map.put("about", about.findAllByOrderByIdAsc());
        map.put("mark", mark.findAllByOrderByIdAsc());
        map.put("status", status.findAllByOrderByIdAsc());
        return map;
    }

    public List<Long> getAllIds(String ddName) {
        switch (ddName) {
            case "about":
                return about.getAllIds();
            case "control":
                return control.getAllIds();
            case "mark":
                return mark.getAllIds();
            case "status":
                return status.getAllIds();
            default:
                return null;
        }
    }

    public BuhDropdownControlEntity getControlById(Long id) {
        return control.findById(id).orElse(null);
    }

    public BuhDropdownAboutEntity getAboutById(Long id) {
        return about.findById(id).orElse(null);
    }

    public BuhDropdownMarkEntity getMarkById(Long id) {
        return mark.findById(id).orElse(null);
    }

    public BuhDropdownStatusEntity getStatusById(Long id) {
        return status.findById(id).orElse(null);
    }

}
