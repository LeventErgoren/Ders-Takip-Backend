package com.leventergoren.service;

import com.leventergoren.dto.DtoOgrenci;
import com.leventergoren.dto.DtoUser;
import com.leventergoren.dto.AuthRequest;
import com.leventergoren.dto.RegisterRequest;
import com.leventergoren.jwt.AuthResponse;
import com.leventergoren.jwt.RefreshTokenRequest;

public interface IAuthService {

    DtoOgrenci register(RegisterRequest request);

    AuthResponse authenticate(AuthRequest request);

    AuthResponse refreshToken(RefreshTokenRequest request);
}
