package com.leventergoren.controller.impl;

import com.leventergoren.controller.IRestOgrenciController;
import com.leventergoren.dto.DtoOgrenci;
import com.leventergoren.service.IOgrenciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ogrenci")
public class RestOgrenciControllerImpl implements IRestOgrenciController {

    @Autowired
    IOgrenciService ogrenciService;

    @GetMapping("/get/{id}")
    @PreAuthorize("#id == principal.id")
    @Override
    public DtoOgrenci getOgrenci(@PathVariable(value = "id") Long id) {
        return ogrenciService.getOgrenci(id);
    }
}
