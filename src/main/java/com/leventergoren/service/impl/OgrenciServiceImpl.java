package com.leventergoren.service.impl;

import com.leventergoren.dto.DtoOgrenci;
import com.leventergoren.exception.BaseException;
import com.leventergoren.exception.ErrorMessage;
import com.leventergoren.exception.MessageType;
import com.leventergoren.model.Ogrenci;
import com.leventergoren.repository.OgrenciRepository;
import com.leventergoren.service.IOgrenciService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OgrenciServiceImpl implements IOgrenciService {

    @Autowired
    OgrenciRepository ogrenciRepository;

    @Override
    public DtoOgrenci getOgrenci(Long id) {
        Optional<Ogrenci> optional = ogrenciRepository.findById(id);

        if (optional.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "->" + id));
        }

        Ogrenci ogrenci = optional.get();
        DtoOgrenci dtoOgrenci = new DtoOgrenci();
        BeanUtils.copyProperties(ogrenci, dtoOgrenci);

        return dtoOgrenci;
    }
}
