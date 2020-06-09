package hu.learnerbot.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController
{
    private static final String VIEW_LOGIN = "pages/login";

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request)
    {
        ModelAndView modelAndView = new ModelAndView(VIEW_LOGIN);
        if (request.getParameter("error") != null) {
            modelAndView.addObject("isLoginError", true);
        }
        if (request.getParameter("logout") != null) {
            modelAndView.addObject("isLogoutSuccess", true);
        }
        return modelAndView;
    }
}
