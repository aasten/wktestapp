package com.epam.wkoneid.controllers;

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
        if(("test").equals(name) && ("test").equals(password)) {
            model.addAttribute("authorized", name);
            // also setting session attribute of successful authorization
            // this also will be saved if mistaken post-request is sent after successful login
            request.getSession().setAttribute("authorized", name);
            if (request.getParameter("rememberme") != null) {
                // checkbox is checked

            }
        }
        return "welcome";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model, HttpServletRequest request) {
        HttpSession s = request.getSession(false);
        if(null != s) {
            String authorizedUser = (String) s.getAttribute("authorized");
            if(authorizedUser != null) {
                model.addAttribute("authorized", authorizedUser);
            }
        }
        return "welcome";
    }
}
