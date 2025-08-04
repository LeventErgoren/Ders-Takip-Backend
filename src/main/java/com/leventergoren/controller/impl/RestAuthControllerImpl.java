package com.leventergoren.controller.impl;

import com.leventergoren.controller.IRestAuthController;
import com.leventergoren.dto.DtoOgrenci;
import com.leventergoren.dto.AuthRequest;
import com.leventergoren.dto.RegisterRequest;
import com.leventergoren.jwt.AuthResponse;
import com.leventergoren.jwt.RefreshTokenRequest;
import com.leventergoren.service.IAuthService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
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
    public DtoOgrenci register(@Valid @RequestBody RegisterRequest request, HttpServletRequest httpServletRequest) {
        return iAuthService.register(request, httpServletRequest.getRemoteAddr());
    }

    @PostMapping("/authenticate")
    @Override
    public String authenticate(@Valid @RequestBody AuthRequest request,HttpServletRequest httpServletRequest) {
        return "asdasdad";
        // return iAuthService.authenticate(request,httpServletRequest.getRemoteAddr());
    }

    @PostMapping("/refreshToken")
    @Override
    public AuthResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshToken) {
        return iAuthService.refreshToken(refreshToken);
    }
}
