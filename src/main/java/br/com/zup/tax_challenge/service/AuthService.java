package br.com.zup.tax_challenge.service;

import br.com.zup.tax_challenge.dto.LoginDTO;

public interface AuthService {
    String login(LoginDTO loginDTO);
}
