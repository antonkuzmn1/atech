package atech.reg.backend.session;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/session")
public class SessionController {

    private final SessionService service;

    public SessionController(SessionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Long> get(HttpSession session) {
        return service.get(session);
    }

    @PostMapping("/auth")
    public void auth(HttpSession session, @RequestBody String jsonString) {
        service.auth(session, jsonString);
    }

    @GetMapping("/logout")
    public void logout(HttpSession session) {
        service.logout(session);
    }

}
