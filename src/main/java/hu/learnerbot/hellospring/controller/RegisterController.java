package hu.learnerbot.hellospring.controller;

import hu.learnerbot.hellospring.model.Role;
import hu.learnerbot.hellospring.model.User;
import hu.learnerbot.hellospring.repository.UserRepository;
import hu.learnerbot.hellospring.request.UserRegisterRequest;
import hu.learnerbot.hellospring.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RegisterController {
    private static final String VIEW_PATH = "pages/register";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SimpleEmailService emailService;

    @Autowired
    public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder, SimpleEmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView(VIEW_PATH);
        modelAndView.addObject("userRegisterRequest", new UserRegisterRequest());
        return modelAndView;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ModelAndView submitRegister(@Valid @ModelAttribute UserRegisterRequest userRegisterRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView(VIEW_PATH, bindingResult.getModel());
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView(new RedirectView("/login"));
        try {
            User user = new User(
                    userRegisterRequest.getName(),
                    userRegisterRequest.getEmail(),
                    passwordEncoder.encode(userRegisterRequest.getPassword()),
                    Role.ROLE_USER);
            userRepository.save(user);
            emailService.sendSimpleMail(user.getEmail(), "Welcome", "Hello Spring Boot!");
        } catch (Exception ex) {
            modelAndView.addObject("hasError", true);
        }
        return modelAndView;
    }
}
