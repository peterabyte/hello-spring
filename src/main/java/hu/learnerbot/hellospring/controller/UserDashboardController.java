package hu.learnerbot.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserDashboardController
{
    private static final String VIEW_PATH = "pages/user/dashboard";

    @RequestMapping(value = "/user/dashboard", method = RequestMethod.GET)
    public ModelAndView user()
    {
        ModelAndView modelAndView = new ModelAndView(VIEW_PATH);
        return modelAndView;
    }
}
