package com.leventergoren.controller.impl;

import com.leventergoren.controller.IRestCalismaSuresiController;
import com.leventergoren.dto.DtoCalismaSuresi;
import com.leventergoren.dto.PageableCalismaSuresiRequest;
import com.leventergoren.jwt.JwtService;
import com.leventergoren.model.ZamanAraligi;
import com.leventergoren.service.ICalismaSuresiService;
import com.leventergoren.utils.RestPageableEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public DtoCalismaSuresi addCalismaSuresi(@PathVariable(required = true) Long id, @RequestParam(required = true) int dakika) {
        return calismaSuresiService.addCalismaSuresi(id, dakika);
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
