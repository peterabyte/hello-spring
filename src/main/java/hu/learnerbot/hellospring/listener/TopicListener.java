package hu.learnerbot.hellospring.listener;

import hu.learnerbot.hellospring.model.Topic;
import hu.learnerbot.hellospring.service.SlugifyService;
import org.springframework.util.StringUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Random;

public class TopicListener {

    @PrePersist
    public void prePersist(Topic topic) {
        setSlug(topic);
    }

    @PreUpdate
    public void preUpdate(Topic topic) {
        setSlug(topic);
    }

    private void setSlug(Topic topic) {
        if (StringUtils.isEmpty(topic.getSlug())) {
            String slug = SlugifyService.generateSlug(topic.getName());
            int min = 10000;
            int max = 99999;
            int index = ((new Random()).nextInt((max - min) + 1) + min);
            slug += "-" + index;
            topic.setSlug(slug);
        }
    }
}
