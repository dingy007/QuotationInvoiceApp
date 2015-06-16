package com.koreinfo.quoteapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.koreinfo.quoteapp.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
