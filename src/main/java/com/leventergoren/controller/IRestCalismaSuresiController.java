package com.leventergoren.controller;

import com.leventergoren.dto.DtoCalismaSuresi;
import com.leventergoren.model.ZamanAraligi;

import java.util.List;

public interface IRestCalismaSuresiController {

    List<DtoCalismaSuresi> getCalismaSuresi(Long id);

    List<DtoCalismaSuresi> getCalismaSuresiWithTime(Long id, ZamanAraligi aralik);

    DtoCalismaSuresi addCalismaSuresi(Long id, int dakika);

}
