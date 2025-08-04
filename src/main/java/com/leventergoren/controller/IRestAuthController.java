package com.leventergoren.controller;

import com.leventergoren.dto.DtoOgrenci;
import com.leventergoren.dto.DtoUser;
import com.leventergoren.dto.AuthRequest;
import com.leventergoren.dto.RegisterRequest;
import com.leventergoren.jwt.AuthResponse;
import com.leventergoren.jwt.RefreshTokenRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface IRestAuthController {

    DtoOgrenci register(RegisterRequest request, HttpServletRequest httpServletRequest);

    AuthResponse authenticate(AuthRequest request, HttpServletRequest httpServletRequest);

    AuthResponse refreshToken(RefreshTokenRequest refreshToken);

}
