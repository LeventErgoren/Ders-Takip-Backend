package com.ders.service;

import com.ders.dto.DtoCalismaSuresi;
import com.ders.dto.PageableCalismaSuresiRequest;
import com.ders.model.CalismaSuresi;
import com.ders.model.ZamanAraligi;
import com.ders.utils.RestPageableEntity;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface ICalismaSuresiService {

    List<DtoCalismaSuresi> getCalismaSuresi(Long id);

    DtoCalismaSuresi addCalismaSuresi(Long id, int dakika, String ipAddress);

    DtoCalismaSuresi addCalismaSuresiWithTime(Long id, int dakika, LocalDate date, String ipAddress);

    List<DtoCalismaSuresi> getCalismaSuresiWithTime(Long id, ZamanAraligi aralik);

    RestPageableEntity<DtoCalismaSuresi> findPageableCalismaSuresi(PageableCalismaSuresiRequest pageable);

}

