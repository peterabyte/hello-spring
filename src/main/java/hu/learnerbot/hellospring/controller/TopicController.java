package hu.learnerbot.hellospring.controller;

import hu.learnerbot.hellospring.model.Comment;
import hu.learnerbot.hellospring.model.Topic;
import hu.learnerbot.hellospring.model.User;
import hu.learnerbot.hellospring.repository.CommentRepository;
import hu.learnerbot.hellospring.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/topics")
public class TopicController {

    private final String VIEW_PATH_LIST = "pages/topic/list";
    private final String VIEW_PATH_EDIT = "pages/topic/edit";
    private final String VIEW_PATH_SHOW = "pages/topic/show";

    private final TopicRepository topicRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public TopicController(TopicRepository topicRepository, CommentRepository commentRepository) {
        this.topicRepository = topicRepository;
        this.commentRepository = commentRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView(VIEW_PATH_LIST);
        modelAndView.addObject("topics", topicRepository.findAll());
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit() {
        ModelAndView modelAndView = new ModelAndView(VIEW_PATH_EDIT);
        modelAndView.addObject("topic", new Topic());
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView submitEdit(@Valid @ModelAttribute Topic topic, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView(VIEW_PATH_EDIT, bindingResult.getModel());
            return modelAndView;
        }

        try {
            topicRepository.save(topic);
            ModelAndView modelAndView = new ModelAndView(new RedirectView("/topics/" + topic.getSlug()));
            return modelAndView;
        } catch (Exception ex) {
            ModelAndView modelAndView = new ModelAndView(new RedirectView("/topics"));
            modelAndView.addObject("hasError", true);
            return modelAndView;
        }
    }

    @RequestMapping(value = "/{topicSlug}", method = RequestMethod.GET)
    public ModelAndView show(@PathVariable(value = "topicSlug") String topicSlug) {
        ModelAndView modelAndView = new ModelAndView(VIEW_PATH_SHOW);
        Topic topic = topicRepository.findBySlug(topicSlug);
        modelAndView.addObject("topic", topic);
        modelAndView.addObject("comments", commentRepository.findByTopicId(topic.getId()));
        modelAndView.addObject("comment", new Comment());
        return modelAndView;
    }

    @RequestMapping(value = "/{topicSlug}/comment", method = RequestMethod.POST)
    public ModelAndView submitComment(@PathVariable(value = "topicSlug") String topicSlug, @Valid @ModelAttribute Comment comment, BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView(VIEW_PATH_EDIT, bindingResult.getModel());
            return modelAndView;
        }

        try {
            User user = (User) authentication.getPrincipal();
            Topic topic = topicRepository.findBySlug(topicSlug);
            comment.setTopic(topic);
            comment.setUser(user);
            commentRepository.save(comment);
            topic.touch();
            topicRepository.save(topic);
            ModelAndView modelAndView = new ModelAndView(new RedirectView("/topics/" + topic.getSlug()));
            return modelAndView;
        } catch (Exception ex) {
            ModelAndView modelAndView = new ModelAndView(new RedirectView("/topics"));
            modelAndView.addObject("hasError", true);
            return modelAndView;
        }
    }
}
