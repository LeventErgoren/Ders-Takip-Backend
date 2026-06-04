package com.ders.service.impl;

import com.ders.dto.DtoCalismaSuresi;
import com.ders.dto.PageableCalismaSuresiRequest;
import com.ders.exception.BaseException;
import com.ders.exception.ErrorMessage;
import com.ders.exception.MessageType;
import com.ders.model.*;
import com.ders.repository.CalismaSuresiRepository;
import com.ders.repository.OgrenciRepository;
import com.ders.repository.UserRepository;
import com.ders.service.ICalismaSuresiService;
import com.ders.utils.RestPageableEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Service
public class CalismaSuresiServiceImpl extends LoggerService implements ICalismaSuresiService {

    @Autowired
    private CalismaSuresiRepository calismaSuresiRepository;

    @Autowired
    private OgrenciRepository ogrenciRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public RestPageableEntity<DtoCalismaSuresi> findPageableCalismaSuresi(PageableCalismaSuresiRequest pageable) {
        Sort.Direction direction = pageable.getSort().equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable1 = PageRequest.of(pageable.getPage(), 10, Sort.by(direction, "creationDate"));

        Page<CalismaSuresi> page = calismaSuresiRepository.findByOgrenciId(pageable.getId(), pageable1);
        RestPageableEntity<DtoCalismaSuresi> pageableEntity = new RestPageableEntity<DtoCalismaSuresi>();
        pageableEntity.setContent(getDtoCalismaSuresiPageable(page));
        pageableEntity.setPageSize(page.getSize());
        pageableEntity.setPageNumber(page.getNumber());
        pageableEntity.setTotalElement(page.getTotalElements());

        return pageableEntity;
    }


    @Override
    public DtoCalismaSuresi addCalismaSuresiWithTime(Long id, int dakika, LocalDate date, String ipAddress) {
        return addCalisma(id, dakika, date, ipAddress, Action.ADD_CALISMA_SURESİ_WITH_TIME);
    }

    private List<DtoCalismaSuresi> getDtoCalismaSuresiPageable(Page<CalismaSuresi> calismaSuresiPage) {
        if (calismaSuresiPage.hasContent()) {
            List<DtoCalismaSuresi> dtoCalismaSuresiList = new ArrayList<>();
            for (CalismaSuresi c : calismaSuresiPage.getContent()) {
                DtoCalismaSuresi dtoCalismaSuresi = new DtoCalismaSuresi();
                BeanUtils.copyProperties(c, dtoCalismaSuresi);
                dtoCalismaSuresiList.add(dtoCalismaSuresi);
            }
            return dtoCalismaSuresiList;
        }
        return null;
    }

    @Override
    @Transactional
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
    public DtoCalismaSuresi addCalismaSuresi(Long id, int dakika, String ipAddress) {
        return addCalisma(id, dakika, null, ipAddress, Action.ADD_CALISMA_SURESİ);
    }

    @Override
    @Transactional
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

    @Transactional
    private DtoCalismaSuresi addCalisma(Long id, int dakika, LocalDate date, String ipAddress, Action action) {
        if (dakika < 1) {
            throw new BaseException(new ErrorMessage(MessageType.TIME_CANT_UNDER, MessageType.TIME_CANT_UNDER.getMessage()));
        }
        if (dakika > 1000) {
            throw new BaseException(new ErrorMessage(MessageType.TIME_CANT_UPPER, MessageType.TIME_CANT_UPPER.getMessage()));
        }

        if (date != null) {
            boolean after = date.isAfter(LocalDate.now());
            if (after) {
                System.out.println(LocalDate.now());
                throw new BaseException(new ErrorMessage(MessageType.DATE_CANT_UPPER, "Eklenecek tarih şimdiden sonra olamaz"));
            }
        }

        Optional<Ogrenci> optional = ogrenciRepository.findById(id);

        if (optional.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, ""));
        }

        LocalDate tarih = date != null ? date : LocalDate.now();

        CalismaSuresi calismaSuresi = new CalismaSuresi();
        calismaSuresi.setCreationDate(tarih);
        calismaSuresi.setDakika(dakika);
        calismaSuresi.setOgrenci(optional.get());

        CalismaSuresi dbCalismaSuresi = calismaSuresiRepository.save(calismaSuresi);

        DtoCalismaSuresi dtoCalismaSuresi = new DtoCalismaSuresi();
        BeanUtils.copyProperties(dbCalismaSuresi, dtoCalismaSuresi);

        Optional<Kullanici> k = userRepository.findById(id);
        log(k.get().getUsername(), action, ipAddress);
        return dtoCalismaSuresi;
    }

}

