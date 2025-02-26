package com.kbds.study.todo.login.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbds.study.todo.login.model.UserDto;
import com.kbds.study.todo.login.repository.LoginRepository;

@Service
public class LoginService {
  private final LoginRepository loginRepository;

  public LoginService(LoginRepository loginRepository){
    this.loginRepository = loginRepository;
  }

  @Transactional
  public UserDto findByUserId(String loginId) {
    UserDto user = loginRepository.selectUser(loginId);
    return user;
  }
}
