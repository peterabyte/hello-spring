package hu.learnerbot.hellospring.service;

import hu.learnerbot.hellospring.model.User;
import hu.learnerbot.hellospring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService
{
    private final UserRepository users;

    @Autowired
    public CustomUserDetailsService(UserRepository users)
    {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException
    {
        UserDetails loadedUser = null;

        try {
            User user = users.findByEmail(userEmail);
            if (user != null) {
                loadedUser = user;
            }
        } catch (Exception repositoryProblem) {
            throw new UsernameNotFoundException(repositoryProblem.getMessage(), repositoryProblem);
        }

        if (loadedUser == null) {
            throw new UsernameNotFoundException("UserDetailsService returned null, user or role not found.");
        }

        return loadedUser;
    }
}
