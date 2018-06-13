package hu.learnerbot.hellospring.controller;

import hu.learnerbot.hellospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminDashboardController
{
    private static final String VIEW_PATH = "pages/admin/dashboard";

    private final UserRepository userRepository;

    @Autowired
    public AdminDashboardController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
    public ModelAndView admin()
    {
        ModelAndView modelAndView = new ModelAndView(VIEW_PATH);
        modelAndView.addObject("users", userRepository.findAll());
        return modelAndView;
    }
}
