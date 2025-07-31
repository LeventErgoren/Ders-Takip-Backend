package com.leventergoren.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXIST("1001", "Kayıt bulunamadı."),
    USERNAME_ALREADY_USING("1002", "Bu kullanıcı adı zaten kullanılıyor"),
    REFRESH_TOKEN_NOT_FOUND("1003", "Böyle bir refresh token bulunamadı"),
    REFRESH_TOKEN_IS_EXPIRED("1004", "Refresh tokenın süresi geçmiş"),
    USERNAME_OR_PASSWORD_WRONG("1005", "Kullanıcı adı veya şifre hatalı"),
    EMAIL_ALREADY_USING("1006", "Bu email zaten kullanılıyor"),
    REGISTER_EXCEPTION("1007", "Kayıt esnasında bir sorun oluştu"),
    TOKEN_IS_EXPIRED("1008", "Tokenın süresi dolmuştur"),
    TIME_CANT_UNDER("1009", "Eklenecek zaman 1'in altında olamaz"),
    TIME_CANT_UPPER("1010", "Eklenecek zaman 1000'in üzerinde olamaz"),
    DATE_CANT_UPPER("1011", "Eklenecek tarih şimdiden sonra olamaz"),
    GENERAL_EXCEPTION("9999", "Genel bir sorun ortaya çıktı.");

    private String code;
    private String message;

    MessageType(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
