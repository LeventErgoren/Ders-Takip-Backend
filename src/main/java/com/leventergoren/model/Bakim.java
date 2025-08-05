package com.leventergoren.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bakim {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private Date startDate;

    @Column
    private Date finishDate;

    @Column
    private String scheduledTime;

    @Column
    private boolean isMaintenance;

    @Column
    private String reason;

}
