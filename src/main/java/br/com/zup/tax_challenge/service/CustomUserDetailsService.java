package br.com.zup.tax_challenge.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetailsService {
    UserDetails loadUserByUsername(String username);
}
