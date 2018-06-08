package hu.learnerbot.hellospring.controller;

import hu.learnerbot.hellospring.model.User;
import hu.learnerbot.hellospring.repository.UserRepository;
import hu.learnerbot.hellospring.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController
{
    private static final String VIEW_PATH = "pages/user/edit";

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }


    @RequestMapping(value = "/user/edit", method = RequestMethod.GET)
    public ModelAndView edit(Authentication authentication)
    {
        ModelAndView modelAndView = new ModelAndView(VIEW_PATH);
        User user = (User)authentication.getPrincipal();
        modelAndView.addObject("user", new UserRequest(user.getName(), user.getEmail()));
        return modelAndView;
    }

    @RequestMapping(value = "/user/edit", method = RequestMethod.POST)
    public ModelAndView submitEdit(@Valid @ModelAttribute UserRequest user, BindingResult bindingResult, Authentication authentication)
    {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView(VIEW_PATH, bindingResult.getModel());
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView(new RedirectView("/user/edit"));
        try {
            User userDetails = (User)authentication.getPrincipal();
            userDetails.setName(user.getName());
            userDetails.setEmail(user.getEmail());
            userRepository.save(userDetails);
        } catch (Exception ex) {
            modelAndView.addObject("hasError", true);
        }
        return modelAndView;
    }
}
