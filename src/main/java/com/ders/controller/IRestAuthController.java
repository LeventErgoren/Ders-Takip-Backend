package com.ders.controller;

import com.ders.dto.*;
import com.ders.jwt.AuthResponse;
import com.ders.jwt.RefreshTokenRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface IRestAuthController {

    DtoOgrenci register(RegisterRequest request, HttpServletRequest httpServletRequest);

    AuthResponse authenticate(AuthRequest request, HttpServletRequest httpServletRequest);

    AuthResponse refreshToken(RefreshTokenRequest refreshToken);

    DtoBakim isMaintenance();

}

