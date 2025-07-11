package com.leventergoren.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoOgrenci {

    private String firstname;

    private String lastname;

    private String email;

    private Date creationDate;

}
