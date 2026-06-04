package com.ders.service;

import com.ders.dto.*;
import com.ders.jwt.AuthResponse;
import com.ders.jwt.RefreshTokenRequest;
import com.ders.model.Bakim;

public interface IAuthService {

    DtoOgrenci register(RegisterRequest request, String ipAddress);

    AuthResponse authenticate(AuthRequest request, String ipAddress);

    AuthResponse refreshToken(RefreshTokenRequest request);

    DtoBakim isMaintenance();
}

