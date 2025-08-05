package com.leventergoren.service;

import com.leventergoren.dto.*;
import com.leventergoren.jwt.AuthResponse;
import com.leventergoren.jwt.RefreshTokenRequest;
import com.leventergoren.model.Bakim;

public interface IAuthService {

    DtoOgrenci register(RegisterRequest request, String ipAddress);

    AuthResponse authenticate(AuthRequest request, String ipAddress);

    AuthResponse refreshToken(RefreshTokenRequest request);

    DtoBakim isMaintenance();
}
