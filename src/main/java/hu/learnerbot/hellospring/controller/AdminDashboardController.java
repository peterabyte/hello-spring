package hu.learnerbot.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminDashboardController
{
    private static final String VIEW_PATH = "pages/admin/dashboard";

    @RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
    public ModelAndView admin()
    {
        ModelAndView modelAndView = new ModelAndView(VIEW_PATH);
        return modelAndView;
    }
}
