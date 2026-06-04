package com.ders.model;

import lombok.Getter;

@Getter
public enum Action {
    REGISTER("Kayıt oldu"),
    LOGIN("Giriş Yaptı"),
    ADD_CALISMA_SURESİ("Çalışma Süresi Ekledi"),
    ADD_CALISMA_SURESİ_WITH_TIME("Belirli Bir Tarihe Çalışma Süresi Ekledi");

    private String detail;

    Action(String detail) {
        this.detail = detail;
    }
}

