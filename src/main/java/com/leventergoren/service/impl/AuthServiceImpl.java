package com.leventergoren.service.impl;

import com.leventergoren.dto.DtoOgrenci;
import com.leventergoren.dto.DtoUser;
import com.leventergoren.dto.RegisterRequest;
import com.leventergoren.exception.BaseException;
import com.leventergoren.exception.ErrorMessage;
import com.leventergoren.exception.MessageType;
import com.leventergoren.dto.AuthRequest;
import com.leventergoren.jwt.AuthResponse;
import com.leventergoren.jwt.JwtService;
import com.leventergoren.jwt.RefreshTokenRequest;
import com.leventergoren.model.Kullanici;
import com.leventergoren.model.Ogrenci;
import com.leventergoren.model.RefreshToken;
import com.leventergoren.repository.OgrenciRepository;
import com.leventergoren.repository.RefreshTokenRepository;
import com.leventergoren.repository.UserRepository;
import com.leventergoren.service.IAuthService;
import jakarta.transaction.Transactional;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.tool.schema.spi.SqlScriptException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private OgrenciRepository ogrenciRepository;

    @Override
    public DtoOgrenci register(RegisterRequest request) {
        try {
            Kullanici kullanici = new Kullanici();
            request.setUsername(request.getUsername().trim());

            BeanUtils.copyProperties(request, kullanici);
            kullanici.setPassword(passwordEncoder.encode(request.getPassword()));


            Ogrenci ogrenci = new Ogrenci();
            ogrenci.setKullanici(kullanici);
            ogrenci.setFirstname(request.getFirstname());
            ogrenci.setLastname(request.getLastname());
            ogrenci.setEmail(request.getEmail());
            ogrenci.setCreationDate(new Date());

            Ogrenci dbOgrenci = ogrenciRepository.save(ogrenci);
            Kullanici dbKullanici = userRepository.save(kullanici);

            DtoOgrenci dtoOgrenci = new DtoOgrenci();
            BeanUtils.copyProperties(dbOgrenci, dtoOgrenci);

            return dtoOgrenci;
        } catch (Exception ex) {

            if (ex.getMessage().contains("username")) {
                throw new BaseException(new ErrorMessage(MessageType.USERNAME_ALREADY_USING, "-> " + request.getUsername()));
            } else if (ex.getMessage().contains("email")) {
                throw new BaseException(new ErrorMessage(MessageType.EMAIL_ALREADY_USING, "-> " + request.getEmail()));
            } else {
                throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, "-> " + ex.getMessage()));

            }

        }
    }

    @Override
    @Transactional
    public AuthResponse authenticate(AuthRequest request) {

        try {
            request.setUsername(request.getUsername().trim());

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
            authenticationProvider.authenticate(auth);

            Optional<Kullanici> optional = userRepository.findByUsername(request.getUsername());
            String accessToken = jwtService.generateToken(optional.get());

            RefreshToken refreshToken = createRefreshToken(optional.get());
            refreshTokenRepository.save(refreshToken);

            return new AuthResponse(accessToken, refreshToken.getRefreshToken());

        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_WRONG, ""));
        }

    }

    @Override
    @Transactional
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        Optional<RefreshToken> optional = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
        if (optional.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, "-> " + request.getRefreshToken()));
        }

        RefreshToken refreshToken = optional.get();

        if (!isRefreshTokenExpired(refreshToken.getExpireDate())) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, request.getRefreshToken()));
        }

        String accessToken = jwtService.generateToken(refreshToken.getUser());
        RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(refreshToken.getUser()));

        return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
    }

    private boolean isRefreshTokenExpired(Date expiredDate) {
        return (new Date().before(expiredDate));
    }

    private RefreshToken createRefreshToken(Kullanici user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 60 *60));
        refreshToken.setUser(user);

        return refreshToken;
    }
}
