package com.leventergoren.repository;

import com.leventergoren.model.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Kullanici, Long> {

    Optional<Kullanici> findByUsername(String username);
}
