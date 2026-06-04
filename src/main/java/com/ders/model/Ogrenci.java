package com.ders.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ogrenci {

    @Id
    private Long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column(unique = true)
    private String email;

    @Column
    private Date creationDate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Kullanici kullanici;

}

