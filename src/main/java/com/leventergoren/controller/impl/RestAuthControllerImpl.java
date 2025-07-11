package com.leventergoren.controller.impl;

import com.leventergoren.controller.IRestAuthController;
import com.leventergoren.dto.DtoOgrenci;
import com.leventergoren.dto.AuthRequest;
import com.leventergoren.dto.RegisterRequest;
import com.leventergoren.jwt.AuthResponse;
import com.leventergoren.jwt.RefreshTokenRequest;
import com.leventergoren.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthControllerImpl implements IRestAuthController {

    @Autowired
    IAuthService iAuthService;

    @PostMapping("/register")
    @Override
    public DtoOgrenci register(@Valid @RequestBody RegisterRequest request) {
        return iAuthService.register(request);
    }

    @PostMapping("/authenticate")
    @Override
    public AuthResponse authenticate(@Valid @RequestBody AuthRequest request) {
        return iAuthService.authenticate(request);
    }

    @PostMapping("/refreshToken")
    @Override
    public AuthResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshToken) {
        return iAuthService.refreshToken(refreshToken);
    }
}
