package com.example.demo.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("mySecurityService")
public class MySecurityService  {
  
   public boolean hasAccess(String parameter) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_"+parameter))) {
        return true;
    }
       return false;
   }

   public boolean Access(String parameter) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(parameter))) {
        return true;
    }
       return false;
   }
}
