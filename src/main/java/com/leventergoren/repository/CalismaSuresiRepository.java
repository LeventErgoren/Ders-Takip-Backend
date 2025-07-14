package com.leventergoren.repository;

import com.leventergoren.model.CalismaSuresi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CalismaSuresiRepository extends JpaRepository<CalismaSuresi, Long> {

    List<CalismaSuresi> findByOgrenci_Id(Long ogrenciId);

    @Query("SELECT c FROM CalismaSuresi c WHERE c.ogrenci.id = :ogrenciId AND c.creationDate >= :startDate")
    List<CalismaSuresi> findByOgrenciAndCreationDateAfter(Long ogrenciId, LocalDate startDate);

}
