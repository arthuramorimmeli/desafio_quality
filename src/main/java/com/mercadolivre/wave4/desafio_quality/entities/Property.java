package com.mercadolivre.wave4.desafio_quality.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @Transient
    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @Transient
    @OneToMany
    private List<Room> rooms = new ArrayList<>();
}
