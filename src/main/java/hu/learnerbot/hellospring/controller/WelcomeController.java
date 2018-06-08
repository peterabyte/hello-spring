package hu.learnerbot.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController
{
    private static final String VIEW_PATH = "pages/user/welcome";

    @RequestMapping(value = "/user/welcome", method = RequestMethod.GET)
    public ModelAndView welcome()
    {
        ModelAndView modelAndView = new ModelAndView(VIEW_PATH);
        return modelAndView;
    }
}
