package com.ders.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {

    private MessageType messageType;

    private String ofStatic;

    public String prepareErrorMessage() {
        String mesaj = "";
        mesaj = messageType.getMessage();
        if (this.ofStatic != null) {
            mesaj += " " + this.ofStatic;
        }
        return mesaj;
    }

}

