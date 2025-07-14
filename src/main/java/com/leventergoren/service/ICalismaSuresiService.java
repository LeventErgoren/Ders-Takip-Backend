package com.leventergoren.service;

import com.leventergoren.dto.DtoCalismaSuresi;
import com.leventergoren.model.ZamanAraligi;

import java.util.List;

public interface ICalismaSuresiService {

    List<DtoCalismaSuresi> getCalismaSuresi(Long id);

    DtoCalismaSuresi addCalismaSuresi(Long id, int dakika);

    List<DtoCalismaSuresi> getCalismaSuresiWithTime(Long id, ZamanAraligi aralik);
}
