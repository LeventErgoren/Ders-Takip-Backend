package com.leventergoren.service.impl;

import com.leventergoren.dto.DtoCalismaSuresi;
import com.leventergoren.exception.BaseException;
import com.leventergoren.exception.ErrorMessage;
import com.leventergoren.exception.MessageType;
import com.leventergoren.model.CalismaSuresi;
import com.leventergoren.model.Ogrenci;
import com.leventergoren.model.ZamanAraligi;
import com.leventergoren.repository.CalismaSuresiRepository;
import com.leventergoren.repository.OgrenciRepository;
import com.leventergoren.service.ICalismaSuresiService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class CalismaSuresiServiceImpl implements ICalismaSuresiService {

    @Autowired
    private CalismaSuresiRepository calismaSuresiRepository;

    @Autowired
    private OgrenciRepository ogrenciRepository;

    @Override
    public List<DtoCalismaSuresi> getCalismaSuresi(Long id) {

        List<CalismaSuresi> calismaSureleri = calismaSuresiRepository.findByOgrenci_Id(id);
        List<DtoCalismaSuresi> dtoCalismaSureleri = new ArrayList<>();

        for (CalismaSuresi c : calismaSureleri) {
            DtoCalismaSuresi dtoCalismaSuresi = new DtoCalismaSuresi();
            BeanUtils.copyProperties(c, dtoCalismaSuresi);
            dtoCalismaSureleri.add(dtoCalismaSuresi);
        }

        return dtoCalismaSureleri;
    }

    @Override
    public DtoCalismaSuresi addCalismaSuresi(Long id, int dakika) {

        Optional<Ogrenci> optional = ogrenciRepository.findById(id);

        if (optional.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, ""));
        }

        CalismaSuresi calismaSuresi = new CalismaSuresi();
        calismaSuresi.setCreationDate(LocalDate.now());
        calismaSuresi.setDakika(dakika);
        calismaSuresi.setOgrenci(optional.get());

        CalismaSuresi dbCalismaSuresi = calismaSuresiRepository.save(calismaSuresi);

        DtoCalismaSuresi dtoCalismaSuresi = new DtoCalismaSuresi();
        BeanUtils.copyProperties(dbCalismaSuresi, dtoCalismaSuresi);

        return dtoCalismaSuresi;
    }

    @Override
    public List<DtoCalismaSuresi> getCalismaSuresiWithTime(Long id, ZamanAraligi aralik) {
        LocalDate localDate = calculateStartDate(aralik);
        List<CalismaSuresi> calismaSuresiList = calismaSuresiRepository.findByOgrenciAndCreationDateAfter(id, localDate);
        List<DtoCalismaSuresi> dtoCalismaSuresiList = new ArrayList<>();

        for (CalismaSuresi c : calismaSuresiList) {
            DtoCalismaSuresi dtoCalismaSuresi = new DtoCalismaSuresi();
            BeanUtils.copyProperties(c, dtoCalismaSuresi);
            dtoCalismaSuresiList.add(dtoCalismaSuresi);
        }

        return dtoCalismaSuresiList;
    }

    public LocalDate calculateStartDate(ZamanAraligi aralik) {
        LocalDate today = LocalDate.now();

        switch (aralik) {
            case TODAY:
                return today;
            case WEEK:
                return today.with(DayOfWeek.MONDAY);
            case MONTH:
                return today.withDayOfMonth(1);
            case YEAR:
                return today.withDayOfYear(1);
            default:
                throw new IllegalArgumentException("Geçersiz zaman aralığı: " + aralik);
        }
    }


}
