package org.jwtauth.repository;

import java.util.Optional;

import org.jwtauth.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Userrepository extends JpaRepository<Users, Integer> {

	Optional<Users> findByUsername(String username);

}
