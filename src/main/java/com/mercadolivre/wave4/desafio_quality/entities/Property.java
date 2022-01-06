package com.mercadolivre.wave4.desafio_quality.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @NotNull
    @ManyToOne
    private District district;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<>();

    public void addRoom() {
        this.rooms.forEach(room -> {
        room.setProperty(this);
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return id.equals(property.id) && name.equals(property.name) && district.equals(property.district) && Objects.equals(rooms, property.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, district, rooms);
    }
}
