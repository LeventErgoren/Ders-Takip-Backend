package com.ders.controller.impl;

import com.ders.controller.IRestCalismaSuresiController;
import com.ders.dto.DtoCalismaSuresi;
import com.ders.dto.PageableCalismaSuresiRequest;
import com.ders.exception.BaseException;
import com.ders.exception.ErrorMessage;
import com.ders.exception.MessageType;
import com.ders.jwt.JwtService;
import com.ders.model.ZamanAraligi;
import com.ders.service.ICalismaSuresiService;
import com.ders.utils.RestPageableEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RestCalismaSuresiControllerImpl implements IRestCalismaSuresiController {

    @Autowired
    JwtService jwtService;

    @Autowired
    ICalismaSuresiService calismaSuresiService;

    @PreAuthorize("#id == principal.id")
    @GetMapping("/calisma-sureleri/{id}")
    @Override
    public List<DtoCalismaSuresi> getCalismaSuresi(@PathVariable(value = "id", required = true) Long id) {
        return calismaSuresiService.getCalismaSuresi(id);
    }

    @PreAuthorize("#id == principal.id")
    @GetMapping("/calisma-sureleri-with-time/{id}")
    @Override
    public List<DtoCalismaSuresi> getCalismaSuresiWithTime(@PathVariable(value = "id", required = true) Long id, @RequestParam() ZamanAraligi aralik) {
        return calismaSuresiService.getCalismaSuresiWithTime(id, aralik);
    }

    @PreAuthorize("#id == principal.id")
    @PostMapping("/add-calisma-suresi/{id}")
    @Override
    public DtoCalismaSuresi addCalismaSuresi(@PathVariable(required = true) Long id, @RequestParam(required = true) int dakika, HttpServletRequest httpServletRequest) {
        return calismaSuresiService.addCalismaSuresi(id, dakika,httpServletRequest.getRemoteAddr());
    }

    @PreAuthorize("#id == principal.id")
    @PostMapping("/add-calisma-suresi-time/{id}")
    @Override
    public DtoCalismaSuresi addCalismaSuresiWithTime(@PathVariable(value = "id") Long id, @RequestParam(required = true) int dakika, @RequestParam LocalDate date, HttpServletRequest httpServletRequest) {
        return calismaSuresiService.addCalismaSuresiWithTime(id, dakika, date, httpServletRequest.getRemoteAddr());
    }

    @Override
    @GetMapping("/get-paginated-calisma-suresi")
    public RestPageableEntity<DtoCalismaSuresi> findPageableCalismaSuresi(@Valid @RequestBody PageableCalismaSuresiRequest pageable,
                                                                          HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7); // "Bearer " kısmını at

        // JwtService bean'i burada inject edilmiş olmalı (constructor ya da @Autowired)
        Long tokenUserId = Long.valueOf(jwtService.getClaimsByKey(token, "id").toString());

        if (!tokenUserId.equals(pageable.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Bu veriye erişim yetkiniz yok.");
        }


        return calismaSuresiService.findPageableCalismaSuresi(pageable);
    }


}

