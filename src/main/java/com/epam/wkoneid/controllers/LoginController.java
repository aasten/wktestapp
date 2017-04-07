package com.epam.wkoneid.controllers;

import org.apache.catalina.Context;
import org.apache.catalina.util.SessionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by sten on 05.04.17.
 */
@Controller
public class LoginController {

    public static class Constants {
        public static final int REMEMBERED_SESSION_AGE_SEC = 60*2;
        /* Do not know how to achieve the name of session cookie. Any of the approaches listed here below returns null.
        String s1 = request.getServletContext().getSessionCookieConfig().getName();
        String s2 = request.getSession().getServletContext().getSessionCookieConfig().getName();
        String s3 = servletContext.getSessionCookieConfig().getName(); */
        public static final String SESSION_COOKIE_NAME = "JSESSIONID";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        if(("test").equals(name) && ("test").equals(password)) {
            // setting session attribute of successful authorization
            // this also will be saved if mistaken post-request is sent after successful login
            request.getSession().setAttribute("authorized", name);
            if (request.getParameter("rememberme") != null) {
                request.getSession().setMaxInactiveInterval(Constants.REMEMBERED_SESSION_AGE_SEC);
                for(Cookie c : request.getCookies()) {
                    if(Constants.SESSION_COOKIE_NAME.equals(c.getName())) {
                        c.setMaxAge(Constants.REMEMBERED_SESSION_AGE_SEC);
                        response.addCookie(c);
                        break;
                    }
                }
            }
        }
        return "welcome";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }
}
