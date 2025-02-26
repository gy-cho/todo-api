package com.kbds.study.todo.login.repository;

import org.apache.ibatis.annotations.Mapper;

import com.kbds.study.todo.login.model.UserDto;

@Mapper
public interface LoginRepository {

  UserDto selectUser(String loginId);
}