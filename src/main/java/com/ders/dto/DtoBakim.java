package com.ders.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoBakim {

    private Date startDate;

    private Date finishDate;

    private String scheduledTime;

    private boolean isMaintenance;

    private String reason;

}

