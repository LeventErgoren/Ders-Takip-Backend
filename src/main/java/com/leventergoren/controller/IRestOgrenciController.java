package com.leventergoren.controller;

import com.leventergoren.dto.DtoOgrenci;

public interface IRestOgrenciController {
    DtoOgrenci getOgrenci(Long id);
}
