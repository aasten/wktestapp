package com.epam.wkoneid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by sten on 05.04.17.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if(name == "test" && password == "test") {
            model.addAttribute("authorized", true);
            if (request.getParameter("rememberme") != null) {
                // checkbox is checked
                // also setting session attribute of successful authorization
                // this also will be saved if mistaken post-request is sent after successful login
                request.getSession().setAttribute("authorized", true);
            }
        }
        return "welcome";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model, HttpServletRequest request) {
        HttpSession s = request.getSession(false);
        if(null != s) {
            if(s.getAttribute("authorized") != null) {
                model.addAttribute("authorized", true);
            }
        }
        return "welcome";
    }
}
