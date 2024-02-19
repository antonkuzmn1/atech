package atech.reg.backend.session;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import atech.reg.backend.session.user.UserService;
import jakarta.servlet.http.HttpSession;

@Service
public class SessionService {

    private final UserService userService;

    public SessionService(UserService userService) {
        this.userService = userService;
    }

    @SuppressWarnings("unchecked")
    public List<Long> get(HttpSession session) {
        List<Long> list = (List<Long>) session.getAttribute("groups");
        if (list == null)
            list = new ArrayList<>();
        return list;
    }

    public void auth(HttpSession session, String jsonString) {
        List<Long> list = userService.authUser(jsonString);
        session.setAttribute("groups", list);
    }

    public void logout(HttpSession session) {
        session.setAttribute("groups", new ArrayList<>());
    }

}