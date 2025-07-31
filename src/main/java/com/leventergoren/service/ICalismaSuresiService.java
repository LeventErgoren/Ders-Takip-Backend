package com.leventergoren.service;

import com.leventergoren.dto.DtoCalismaSuresi;
import com.leventergoren.dto.PageableCalismaSuresiRequest;
import com.leventergoren.model.CalismaSuresi;
import com.leventergoren.model.ZamanAraligi;
import com.leventergoren.utils.RestPageableEntity;
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
