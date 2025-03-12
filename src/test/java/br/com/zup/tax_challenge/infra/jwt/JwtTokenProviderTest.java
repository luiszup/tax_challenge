package br.com.zup.tax_challenge.infra.jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JwtTokenProviderTest {
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtTokenProvider = new JwtTokenProvider();
    }

    @Test
    void generateTokenSuccess() {
        String username = "usuarioteste";
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, Collections.singletonList(authority));

        String token = jwtTokenProvider.generateToken(authentication);

        boolean isValid = jwtTokenProvider.validateToken(token);

        assertThat(isValid).isTrue();
    }

    @Test
    void generateTokenFail() {
        Authentication invalidAuthentication = new UsernamePasswordAuthenticationToken(null, null, Collections.emptyList());

        String token = jwtTokenProvider.generateToken(invalidAuthentication);

        boolean isValid = jwtTokenProvider.validateToken(token);

        assertThat(isValid).isFalse();
    }

    @Test
    void validateInvalidToken() {
        String invalidToken = "token_invalido";

        boolean isValid = jwtTokenProvider.validateToken(invalidToken);

        assertThat(isValid).isFalse();
    }

    @Test
    void validateNullToken() {
        String nullToken = null;

        boolean isValid = jwtTokenProvider.validateToken(nullToken);

        assertThat(isValid).isFalse();
    }
}