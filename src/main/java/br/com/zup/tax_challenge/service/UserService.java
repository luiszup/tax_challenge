package br.com.zup.tax_challenge.service;

import br.com.zup.tax_challenge.dto.RegisterUserDTO;
import br.com.zup.tax_challenge.model.User;

public interface UserService {

    User registerUser(RegisterUserDTO registerUserDTO);
}
