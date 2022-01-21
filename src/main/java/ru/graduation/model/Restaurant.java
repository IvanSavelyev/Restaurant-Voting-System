package ru.graduation.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(
        columnNames = "name",
        name = "unique_restaurant")})
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Restaurant extends AbstractNamedEntity {

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @ToString.Exclude
    private Menu menu;
}
