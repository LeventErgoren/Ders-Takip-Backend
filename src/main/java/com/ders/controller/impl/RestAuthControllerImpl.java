package com.ders.controller.impl;

import com.ders.controller.IRestAuthController;
import com.ders.dto.DtoBakim;
import com.ders.dto.DtoOgrenci;
import com.ders.dto.AuthRequest;
import com.ders.dto.RegisterRequest;
import com.ders.jwt.AuthResponse;
import com.ders.jwt.RefreshTokenRequest;
import com.ders.service.IAuthService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthControllerImpl implements IRestAuthController {

    @Autowired
    IAuthService iAuthService;

    @PostMapping("/register")
    @Override
    public DtoOgrenci register(@Valid @RequestBody RegisterRequest request, HttpServletRequest httpServletRequest) {
        return iAuthService.register(request, httpServletRequest.getRemoteAddr());
    }

    @PostMapping("/authenticate")
    @Override
    public AuthResponse authenticate(@Valid @RequestBody AuthRequest request, HttpServletRequest httpServletRequest) {
        return iAuthService.authenticate(request, httpServletRequest.getRemoteAddr());
    }

    @PostMapping("/refreshToken")
    @Override
    public AuthResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshToken) {
        return iAuthService.refreshToken(refreshToken);
    }

    @GetMapping("/is-maintenance")
    @Override
    public DtoBakim isMaintenance() {
        return iAuthService.isMaintenance();
    }
}

