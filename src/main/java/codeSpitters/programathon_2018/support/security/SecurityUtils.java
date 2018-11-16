package codeSpitters.programathon_2018.support.security;

import codeSpitters.programathon_2018.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public SecurityUtils(){

    }

    public static User getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null)
            throw new IllegalStateException("Could not get authentication");

        Object principal = auth.getPrincipal();

        if ((principal instanceof User))
            return (User) principal;

        return null;
    }

}
