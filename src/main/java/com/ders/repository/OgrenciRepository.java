package com.ders.repository;

import com.ders.model.Ogrenci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {

}

