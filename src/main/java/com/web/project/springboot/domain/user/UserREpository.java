package com.web.project.springboot.domain.user;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserREpository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
