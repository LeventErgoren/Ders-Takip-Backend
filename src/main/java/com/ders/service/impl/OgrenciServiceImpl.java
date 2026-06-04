package com.ders.service.impl;

import com.ders.dto.DtoOgrenci;
import com.ders.exception.BaseException;
import com.ders.exception.ErrorMessage;
import com.ders.exception.MessageType;
import com.ders.model.Ogrenci;
import com.ders.repository.OgrenciRepository;
import com.ders.service.IOgrenciService;
import jakarta.transaction.Transactional;
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

