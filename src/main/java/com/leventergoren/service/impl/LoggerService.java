package com.leventergoren.service.impl;

import com.leventergoren.model.Action;
import com.leventergoren.model.Log;
import com.leventergoren.repository.LogRepository;
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
