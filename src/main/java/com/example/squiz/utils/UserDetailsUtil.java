package com.example.squiz.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsUtil {
    default String extractUser(Authentication authentication) {
        String username = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        }

        return username;
    }
}
