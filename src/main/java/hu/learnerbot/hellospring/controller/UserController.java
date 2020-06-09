package hu.learnerbot.hellospring.controller;

import hu.learnerbot.hellospring.model.User;
import hu.learnerbot.hellospring.repository.UserRepository;
import hu.learnerbot.hellospring.request.PasswordRequest;
import hu.learnerbot.hellospring.request.UserDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/user/")
public class UserController
{
    private static final String VIEW_PATH_EDIT = "pages/user/edit";
    private static final String VIEW_PATH_EDIT_PASSWORD = "pages/user/password";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ModelAndView edit(Authentication authentication)
    {
        ModelAndView modelAndView = new ModelAndView(VIEW_PATH_EDIT);
        User user = (User)authentication.getPrincipal();
        modelAndView.addObject("user", new UserDetailsRequest(user.getName(), user.getEmail()));
        return modelAndView;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ModelAndView submitEdit(@Valid @ModelAttribute UserDetailsRequest user, BindingResult bindingResult, Authentication authentication)
    {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView(VIEW_PATH_EDIT, bindingResult.getModel());
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView(new RedirectView("/"));
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

    @RequestMapping(value = "password", method = RequestMethod.GET)
    public ModelAndView password()
    {
        ModelAndView modelAndView = new ModelAndView(VIEW_PATH_EDIT_PASSWORD);
        modelAndView.addObject("passwordRequest", new PasswordRequest());
        return modelAndView;
    }

    @RequestMapping(value = "password", method = RequestMethod.POST)
    public ModelAndView submitPassword(@Valid @ModelAttribute PasswordRequest passwordRequest, BindingResult bindingResult, Authentication authentication)
    {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView(VIEW_PATH_EDIT_PASSWORD, bindingResult.getModel());
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView(new RedirectView("/"));
        try {
            User userDetails = (User)authentication.getPrincipal();
            userDetails.setPassword(passwordEncoder.encode(passwordRequest.getPassword()));
            userRepository.save(userDetails);
        } catch (Exception ex) {
            modelAndView.addObject("hasError", true);
        }
        return modelAndView;
    }
}
