package hu.learnerbot.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WelcomeController
{
    private static final String VIEW_WELCOME = "pages/welcome";

    @RequestMapping(value =  "/", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request)
    {
        ModelAndView modelAndView = new ModelAndView(VIEW_WELCOME);
        return modelAndView;
    }
}
