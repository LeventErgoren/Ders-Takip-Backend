package com.ders.controller;

import com.ders.dto.DtoCalismaSuresi;
import com.ders.dto.PageableCalismaSuresiRequest;
import com.ders.model.ZamanAraligi;
import com.ders.utils.RestPageableEntity;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;

public interface IRestCalismaSuresiController {

    List<DtoCalismaSuresi> getCalismaSuresi(Long id);

    List<DtoCalismaSuresi> getCalismaSuresiWithTime(Long id, ZamanAraligi aralik);

    DtoCalismaSuresi addCalismaSuresi(Long id, int dakika, HttpServletRequest httpServletRequest);

    DtoCalismaSuresi addCalismaSuresiWithTime(Long id, int dakika, LocalDate date, HttpServletRequest httpServletRequest);

    RestPageableEntity<DtoCalismaSuresi> findPageableCalismaSuresi(PageableCalismaSuresiRequest pageable,
                                                                   HttpServletRequest request);

}

