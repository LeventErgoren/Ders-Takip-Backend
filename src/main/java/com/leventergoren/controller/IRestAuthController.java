package com.leventergoren.controller;

import com.leventergoren.dto.DtoOgrenci;
import com.leventergoren.dto.DtoUser;
import com.leventergoren.dto.AuthRequest;
import com.leventergoren.dto.RegisterRequest;
import com.leventergoren.jwt.AuthResponse;
import com.leventergoren.jwt.RefreshTokenRequest;

public interface IRestAuthController {

    DtoOgrenci register(RegisterRequest request);

    AuthResponse authenticate(AuthRequest request);

    AuthResponse refreshToken(RefreshTokenRequest refreshToken);

}
