package com.leventergoren.controller;

import com.leventergoren.dto.DtoCalismaSuresi;
import com.leventergoren.dto.PageableCalismaSuresiRequest;
import com.leventergoren.model.CalismaSuresi;
import com.leventergoren.model.ZamanAraligi;
import com.leventergoren.utils.RestPageableEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IRestCalismaSuresiController {

    List<DtoCalismaSuresi> getCalismaSuresi(Long id);

    List<DtoCalismaSuresi> getCalismaSuresiWithTime(Long id, ZamanAraligi aralik);

    DtoCalismaSuresi addCalismaSuresi(Long id, int dakika);

    RestPageableEntity<DtoCalismaSuresi> findPageableCalismaSuresi(PageableCalismaSuresiRequest pageable,
                                                                   HttpServletRequest request);

}
