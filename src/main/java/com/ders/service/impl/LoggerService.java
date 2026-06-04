package com.ders.service.impl;

import com.ders.model.Action;
import com.ders.model.Log;
import com.ders.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class LoggerService {

    @Autowired
    LogRepository logRepository;

    public void log(String username, Action action, String ipAddress) {
        Log l = new Log();
        l.setDate(new Date());
        l.setUsername(username);
        l.setAction(action);
        l.setDetails(action.getDetail());
        l.setIpAddress(ipAddress);

        logRepository.save(l);
    }


}

