package hu.learnerbot.hellospring.service;

import com.github.slugify.Slugify;
import org.springframework.stereotype.Service;

@Service
public class SlugifyService {

    private static final Slugify slugify = new Slugify();

    public static String generateSlug(String text) {
        return slugify.slugify(text);
    }
}
