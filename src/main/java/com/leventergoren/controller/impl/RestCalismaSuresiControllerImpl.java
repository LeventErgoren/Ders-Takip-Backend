package com.leventergoren.controller.impl;

import com.leventergoren.controller.IRestCalismaSuresiController;
import com.leventergoren.dto.DtoCalismaSuresi;
import com.leventergoren.model.ZamanAraligi;
import com.leventergoren.service.ICalismaSuresiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RestCalismaSuresiControllerImpl implements IRestCalismaSuresiController {

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
}
