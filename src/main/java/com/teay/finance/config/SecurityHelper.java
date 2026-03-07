package com.teay.finance.config;

import com.teay.finance.entities.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityHelper {
    public static Long getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            Object principal = authentication.getPrincipal();
            if(principal instanceof UserPrincipal){
                return ((UserPrincipal) principal).getId();
            }
        }
        return null;
    }
}
