package com.ipung.training.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ipung.training.db.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{

}
